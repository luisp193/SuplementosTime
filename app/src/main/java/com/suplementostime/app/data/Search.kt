package com.suplementostime.app.data

import java.text.Normalizer

/** Búsqueda tolerante a errores: sin tildes, fonética española y distancia de edición. */
object Search {

    fun normalize(input: String): String {
        var s = Normalizer.normalize(input.lowercase(), Normalizer.Form.NFD)
            .replace(Regex("\\p{Mn}+"), "")
            .replace(Regex("[^a-z0-9 ]"), " ")
        s = s.replace("ce", "se").replace("ci", "si")
            .replace("qu", "k").replace("c", "k")
            .replace("ph", "f").replace("v", "b").replace("z", "s")
            .replace("h", "").replace("ll", "y").replace("w", "u")
        s = s.replace(Regex("([a-z])\\1+"), "$1")
        return s.replace(Regex("\\s+"), " ").trim()
    }

    /** Distancia Damerau-Levenshtein (las letras intercambiadas cuentan como 1). */
    fun distance(a: String, b: String): Int {
        val d = Array(a.length + 1) { IntArray(b.length + 1) }
        for (i in 0..a.length) d[i][0] = i
        for (j in 0..b.length) d[0][j] = j
        for (i in 1..a.length) for (j in 1..b.length) {
            val cost = if (a[i - 1] == b[j - 1]) 0 else 1
            var v = minOf(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost)
            if (i > 1 && j > 1 && a[i - 1] == b[j - 2] && a[i - 2] == b[j - 1]) {
                v = minOf(v, d[i - 2][j - 2] + 1)
            }
            d[i][j] = v
        }
        return d[a.length][b.length]
    }

    private fun tokenScore(q: String, t: String): Double {
        if (q == t) return 1.0
        if (t.startsWith(q)) return 0.9
        if (q.length >= 3 && t.contains(q)) return 0.7
        val allowed = when {
            q.length <= 3 -> 0
            q.length <= 5 -> 1
            else -> 2
        }
        if (allowed == 0) return 0.0
        val d = minOf(distance(q, t), distance(q, t.take(q.length)))
        return if (d <= allowed) 0.8 - 0.15 * d else 0.0
    }

    fun score(query: String, s: Supplement): Double {
        val qTokens = normalize(query).split(" ").filter { it.isNotEmpty() }
        if (qTokens.isEmpty()) return 0.0
        val keys = (listOf(s.name) + s.aliases + s.category).map { normalize(it) }
        val tokens = keys.flatMap { it.split(" ") }.filter { it.isNotEmpty() }.distinct()
        var total = 0.0
        for (q in qTokens) {
            // También probar la frase completa contra cada clave (p. ej. "vitamina d").
            val best = maxOf(
                tokens.maxOfOrNull { tokenScore(q, it) } ?: 0.0,
                keys.maxOfOrNull { k -> if (k.length > q.length) tokenScore(q, k) else 0.0 } ?: 0.0,
            )
            if (best == 0.0) return 0.0
            total += best
        }
        val nameBonus = if (normalize(s.name).startsWith(qTokens.first())) 0.05 else 0.0
        return total / qTokens.size + nameBonus
    }

    fun search(query: String, all: List<Supplement>): List<Supplement> {
        if (query.isBlank()) return all
        return all.map { it to score(query, it) }
            .filter { it.second > 0.0 }
            .sortedByDescending { it.second }
            .map { it.first }
    }
}
