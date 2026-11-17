package com.suplementostime.app.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/** Nutriente con su valor de referencia (VRN, Reglamento UE 1169/2011). */
data class Nutrient(val key: String, val name: String, val unit: String, val nrv: Double, val isMineral: Boolean)

object Nutrients {
    val all = listOf(
        Nutrient("a", "Vitamina A", "µg", 800.0, false),
        Nutrient("d", "Vitamina D", "µg", 5.0, false),
        Nutrient("e", "Vitamina E", "mg", 12.0, false),
        Nutrient("k", "Vitamina K", "µg", 75.0, false),
        Nutrient("c", "Vitamina C", "mg", 80.0, false),
        Nutrient("b1", "B1 Tiamina", "mg", 1.1, false),
        Nutrient("b2", "B2 Riboflavina", "mg", 1.4, false),
        Nutrient("b3", "B3 Niacina", "mg", 16.0, false),
        Nutrient("b5", "B5 Pantoténico", "mg", 6.0, false),
        Nutrient("b6", "B6", "mg", 1.4, false),
        Nutrient("b7", "B7 Biotina", "µg", 50.0, false),
        Nutrient("b9", "B9 Folato", "µg", 200.0, false),
        Nutrient("b12", "B12", "µg", 2.5, false),
        Nutrient("ca", "Calcio", "mg", 800.0, true),
        Nutrient("fe", "Hierro", "mg", 14.0, true),
        Nutrient("mg", "Magnesio", "mg", 375.0, true),
        Nutrient("zn", "Zinc", "mg", 10.0, true),
        Nutrient("cu", "Cobre", "mg", 1.0, true),
        Nutrient("mn", "Manganeso", "mg", 2.0, true),
        Nutrient("se", "Selenio", "µg", 55.0, true),
        Nutrient("cr", "Cromo", "µg", 40.0, true),
        Nutrient("mo", "Molibdeno", "µg", 50.0, true),
        Nutrient("i", "Yodo", "µg", 150.0, true),
        Nutrient("k_", "Potasio", "mg", 2000.0, true),
        Nutrient("p", "Fósforo", "mg", 700.0, true),
        Nutrient("epa", "Omega-3 EPA+DHA", "mg", 250.0, false),
    )

    /** Límites superiores orientativos (por día, suplementos) para avisar de excesos. */
    val upper = mapOf(
        "a" to 3000.0, "d" to 100.0, "e" to 300.0, "c" to 1000.0, "b3" to 35.0, "b6" to 12.0,
        "b9" to 1000.0, "ca" to 2500.0, "fe" to 45.0, "mg" to 250.0, "zn" to 25.0, "cu" to 5.0,
        "mn" to 11.0, "se" to 300.0, "mo" to 2000.0, "i" to 600.0,
    )
}

/** Producto (multivitamínico, omega-3, etc.) con cantidades POR DOSIS DIARIA. */
data class Product(val id: String, val brand: String, val name: String, val amounts: Map<String, Double>)

class ProductStore(context: Context) {
    private val prefs = context.getSharedPreferences("productos", Context.MODE_PRIVATE)

    fun load(): List<Product> = try {
        val arr = JSONArray(prefs.getString("items", "[]"))
        (0 until arr.length()).map { i ->
            val o = arr.getJSONObject(i)
            val am = o.getJSONObject("amounts")
            Product(o.getString("id"), o.getString("brand"), o.getString("name"),
                am.keys().asSequence().associateWith { am.getDouble(it) })
        }
    } catch (e: Exception) { emptyList() }

    fun save(items: List<Product>) {
        val arr = JSONArray()
        items.forEach { p ->
            val am = JSONObject(); p.amounts.forEach { (k, v) -> am.put(k, v) }
            arr.put(JSONObject().put("id", p.id).put("brand", p.brand).put("name", p.name).put("amounts", am))
        }
        prefs.edit().putString("items", arr.toString()).apply()
    }
}

object Compare {
    fun percent(n: Nutrient, amount: Double) = amount / n.nrv * 100.0

    fun fmt(v: Double): String =
        if (v == Math.floor(v)) v.toLong().toString() else String.format(java.util.Locale.US, "%.1f", v).replace('.', ',')

    /** Nutrientes presentes en al menos un producto. */
    fun rows(products: List<Product>): List<Nutrient> =
        Nutrients.all.filter { n -> products.any { (it.amounts[n.key] ?: 0.0) > 0.0 } }

    /** Nº de nutrientes entre 50 % y 200 % del VRN, como medida simple de equilibrio. */
    fun balanced(p: Product): Int = Nutrients.all.count {
        val v = p.amounts[it.key] ?: 0.0
        v > 0 && percent(it, v) in 50.0..200.0
    }

    fun overLimit(p: Product): List<Nutrient> = Nutrients.all.filter {
        val lim = Nutrients.upper[it.key]; val v = p.amounts[it.key] ?: 0.0
        lim != null && v > lim
    }
}
