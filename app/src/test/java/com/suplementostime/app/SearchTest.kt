package com.suplementostime.app

import com.suplementostime.app.data.Catalog
import com.suplementostime.app.data.Search
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchTest {
    private fun first(q: String) = Search.search(q, Catalog.all).firstOrNull()?.id

    @Test fun exacto() = assertEquals("creatina", first("creatina"))
    @Test fun sinTildes() = assertEquals("omega3", first("omega 3"))
    @Test fun letraMal() = assertEquals("creatina", first("kreatina"))
    @Test fun letraExtra() = assertEquals("creatina", first("creatinna"))
    @Test fun letrasIntercambiadas() = assertEquals("magnesio", first("mangesio"))
    @Test fun faltaLetra() = assertEquals("cafeina", first("cafena"))
    @Test fun ortografia() = assertEquals("vitd", first("bitamina d"))
    @Test fun nutrientesCompletos() { listOf("zinc","hierro","calcio","omega 3","vitamina b12","multivitaminico").forEach { assertTrue(it, first(it) != null) } }
    @Test fun proteina() = assertEquals("whey", first("proteina"))
    @Test fun vacioDevuelveTodo() = assertEquals(Catalog.all.size, Search.search("", Catalog.all).size)
    @Test fun sinResultados() = assertTrue(Search.search("xqzwv", Catalog.all).isEmpty())
}
