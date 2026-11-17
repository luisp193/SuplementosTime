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
    @Test fun nac() = assertEquals("nac", first("nac"))
    @Test fun acetilcisteina() = assertEquals("nac", first("acetilcisteina"))
    @Test fun coq10() = assertEquals("coq10", first("coenzima q10"))
    @Test fun glutation() = assertEquals("glutation", first("glutation"))
    @Test fun triptofano() = assertEquals("triptofano", first("triptofano"))
    @Test fun cincoHtp() = assertEquals("htp", first("5htp"))
    @Test fun bpc() = assertEquals("peptidos_iny", first("bpc 157"))
    @Test fun arginina() = assertEquals("arginina", first("arjinina"))
    private fun ids(q: String) = Search.search(q, Catalog.all).map { it.id }
    @Test fun valeriana() = assertEquals("valeriana", first("balerana"))
    @Test fun tos() { val r = ids("tos"); assertTrue("tomillo" in r && "hiedra" in r) }
    @Test fun cistitis() { val r = ids("cistitis"); assertTrue("arandano_rojo" in r && "dmanosa" in r) }
    @Test fun dormir() { val r = ids("dormir"); assertTrue("melatonina" in r && "valeriana" in r && "pasiflora" in r) }
    @Test fun hiperico() = assertEquals("hiperico", first("hierba de san juan"))
    @Test fun idsUnicos() = assertEquals(Catalog.all.size, Catalog.all.map { it.id }.toSet().size)
    @Test fun vacioDevuelveTodo() = assertEquals(Catalog.all.size, Search.search("", Catalog.all).size)
    @Test fun sinResultados() = assertTrue(Search.search("xqzwv", Catalog.all).isEmpty())
}
