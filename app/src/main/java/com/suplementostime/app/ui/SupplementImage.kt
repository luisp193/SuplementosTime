package com.suplementostime.app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suplementostime.app.data.Supplement
import kotlin.math.abs

enum class Form { POLVO, CAPSULA, PERLA, COMPRIMIDO, GOTAS, PLANTA }

data class Look(val form: Form, val color: Color)

object Looks {
    private val byId = mapOf(
        "creatina" to Look(Form.POLVO, Color(0xFF4F8FD8)),
        "whey" to Look(Form.POLVO, Color(0xFF8D6E63)),
        "magnesio" to Look(Form.COMPRIMIDO, Color(0xFF7E57C2)),
        "vitd" to Look(Form.PERLA, Color(0xFFF2B233)),
        "omega3" to Look(Form.PERLA, Color(0xFFE09A2C)),
        "cafeina" to Look(Form.CAPSULA, Color(0xFF6D4C41)),
        "vitc" to Look(Form.COMPRIMIDO, Color(0xFFFF8F00)),
        "zinc" to Look(Form.COMPRIMIDO, Color(0xFF78909C)),
        "colageno" to Look(Form.POLVO, Color(0xFFE57373)),
        "ashwagandha" to Look(Form.CAPSULA, Color(0xFF8D9440)),
        "melatonina" to Look(Form.COMPRIMIDO, Color(0xFF5C6BC0)),
        "betaalanina" to Look(Form.POLVO, Color(0xFFEF5350)),
        "citrulina" to Look(Form.POLVO, Color(0xFFFF7043)),
        "b12" to Look(Form.GOTAS, Color(0xFFD81B60)),
        "vita" to Look(Form.PERLA, Color(0xFFFFA000)),
        "vite" to Look(Form.PERLA, Color(0xFFC9A227)),
        "vitk" to Look(Form.GOTAS, Color(0xFF43A047)),
        "folato" to Look(Form.COMPRIMIDO, Color(0xFFFBC02D)),
        "hierro" to Look(Form.COMPRIMIDO, Color(0xFFB71C1C)),
        "calcio" to Look(Form.COMPRIMIDO, Color(0xFF90A4AE)),
        "multi" to Look(Form.CAPSULA, Color(0xFF26A69A)),
        "nac" to Look(Form.CAPSULA, Color(0xFF1E88E5)),
        "glutation" to Look(Form.CAPSULA, Color(0xFF00897B)),
        "ala" to Look(Form.COMPRIMIDO, Color(0xFFFDD835)),
        "coq10" to Look(Form.PERLA, Color(0xFFE65100)),
        "carnitina" to Look(Form.CAPSULA, Color(0xFFAB47BC)),
        "taurina" to Look(Form.POLVO, Color(0xFF29B6F6)),
        "glicina" to Look(Form.POLVO, Color(0xFF9CCC65)),
        "teanina" to Look(Form.CAPSULA, Color(0xFF66BB6A)),
        "glutamina" to Look(Form.POLVO, Color(0xFF5C6BC0)),
        "eaa" to Look(Form.POLVO, Color(0xFFEC407A)),
        "hmb" to Look(Form.CAPSULA, Color(0xFF3949AB)),
        "probioticos" to Look(Form.CAPSULA, Color(0xFF8BC34A)),
        "curcuma" to Look(Form.CAPSULA, Color(0xFFF9A825)),
        "glucosamina" to Look(Form.COMPRIMIDO, Color(0xFF4DB6AC)),
        "msm" to Look(Form.COMPRIMIDO, Color(0xFFB0BEC5)),
        "hialuronico" to Look(Form.CAPSULA, Color(0xFF4FC3F7)),
        "quercetina" to Look(Form.CAPSULA, Color(0xFFFFB300)),
        "inositol" to Look(Form.POLVO, Color(0xFFBA68C8)),
        "rhodiola" to Look(Form.CAPSULA, Color(0xFFD4A017)),
        "electrolitos" to Look(Form.POLVO, Color(0xFF26C6DA)),
        "psyllium" to Look(Form.POLVO, Color(0xFFA1887F)),
        "espirulina" to Look(Form.COMPRIMIDO, Color(0xFF2E7D32)),
        "resveratrol" to Look(Form.CAPSULA, Color(0xFF8E244D)),
        "arginina" to Look(Form.POLVO, Color(0xFFD84315)),
        "leucina" to Look(Form.POLVO, Color(0xFF7CB342)),
        "tirosina" to Look(Form.CAPSULA, Color(0xFFFF7043)),
        "triptofano" to Look(Form.CAPSULA, Color(0xFF3F51B5)),
        "htp" to Look(Form.CAPSULA, Color(0xFF5E35B1)),
        "gaba" to Look(Form.COMPRIMIDO, Color(0xFF7986CB)),
        "ornitina" to Look(Form.CAPSULA, Color(0xFF0097A7)),
        "lisina" to Look(Form.COMPRIMIDO, Color(0xFF81C784)),
        "betaina" to Look(Form.POLVO, Color(0xFFAD1457)),
        "agmatina" to Look(Form.CAPSULA, Color(0xFFC62828)),
        "daa" to Look(Form.POLVO, Color(0xFF455A64)),
        "carnosina" to Look(Form.CAPSULA, Color(0xFF6A1B9A)),
        "whey_hidro" to Look(Form.POLVO, Color(0xFFBCAAA4)),
        "caseina" to Look(Form.POLVO, Color(0xFF5D4037)),
        "colageno_pep" to Look(Form.POLVO, Color(0xFFF06292)),
        "calostro" to Look(Form.POLVO, Color(0xFFFFD54F)),
        "peptidos_iny" to Look(Form.GOTAS, Color(0xFF9E9E9E)),
        // Plantas: hojas con el color típico de la planta o de su flor.
        "valeriana" to Look(Form.PLANTA, Color(0xFF7B9E4A)),
        "pasiflora" to Look(Form.PLANTA, Color(0xFF7E57C2)),
        "melisa" to Look(Form.PLANTA, Color(0xFF8BC34A)),
        "lavanda" to Look(Form.PLANTA, Color(0xFF9575CD)),
        "manzanilla" to Look(Form.PLANTA, Color(0xFFFBC02D)),
        "amapola_cal" to Look(Form.PLANTA, Color(0xFFFF9800)),
        "lupulo" to Look(Form.PLANTA, Color(0xFF9CCC65)),
        "equinacea" to Look(Form.PLANTA, Color(0xFFD81B60)),
        "sauco" to Look(Form.PLANTA, Color(0xFF4A148C)),
        "propoleo" to Look(Form.GOTAS, Color(0xFFB8860B)),
        "pelargonio" to Look(Form.GOTAS, Color(0xFF8D6E63)),
        "tomillo" to Look(Form.PLANTA, Color(0xFF689F38)),
        "hiedra" to Look(Form.PLANTA, Color(0xFF2E7D32)),
        "jengibre" to Look(Form.PLANTA, Color(0xFFD4A24C)),
        "menta" to Look(Form.PLANTA, Color(0xFF00A86B)),
        "alcachofa" to Look(Form.PLANTA, Color(0xFF558B2F)),
        "cardo_mariano" to Look(Form.PLANTA, Color(0xFF8E24AA)),
        "hinojo" to Look(Form.PLANTA, Color(0xFFAFB42B)),
        "arandano_rojo" to Look(Form.CAPSULA, Color(0xFFC2185B)),
        "dmanosa" to Look(Form.POLVO, Color(0xFF64B5F6)),
        "harpagofito" to Look(Form.PLANTA, Color(0xFF8D6E63)),
        "boswellia" to Look(Form.CAPSULA, Color(0xFFC8A165)),
        "hiperico" to Look(Form.PLANTA, Color(0xFFFFC107)),
        "ginkgo" to Look(Form.PLANTA, Color(0xFF9E9D24)),
        "ginseng" to Look(Form.PLANTA, Color(0xFFBF8F4F)),
        "castano_indias" to Look(Form.PLANTA, Color(0xFF795548)),
        "ajo" to Look(Form.PERLA, Color(0xFFD7CCC8)),
    )

    private val palette = listOf(
        Color(0xFF1B7F5C), Color(0xFF3F51B5), Color(0xFFE67E22), Color(0xFF8E24AA),
        Color(0xFF00838F), Color(0xFFC62828), Color(0xFF6D8B3C), Color(0xFFAD7A00),
    )

    fun of(s: Supplement): Look = byId[s.id] ?: Look(
        form = when {
            s.category == "Minerales" -> Form.COMPRIMIDO
            s.category.startsWith("Plantas") -> Form.PLANTA
            else -> Form.CAPSULA
        },
        color = palette[abs(s.id.hashCode()) % palette.size],
    )
}

/**
 * Imagen del suplemento. Si existe una foto real en res/drawable llamada foto_<id>
 * (p. ej. foto_creatina.jpg), se muestra esa; si no, se dibuja una ilustración.
 */
@SuppressLint("DiscouragedApi")
@Composable
fun SupplementImage(s: Supplement, modifier: Modifier = Modifier, corner: Dp = 16.dp) {
    val ctx = LocalContext.current
    val photo = remember(s.id) { ctx.resources.getIdentifier("foto_${s.id}", "drawable", ctx.packageName) }
    Box(modifier.clip(RoundedCornerShape(corner))) {
        if (photo != 0) {
            Image(painterResource(photo), s.name, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        } else {
            val look = remember(s.id) { Looks.of(s) }
            Canvas(Modifier.fillMaxSize()) { drawSupplement(look) }
        }
    }
}

private fun Color.darker(f: Float = 0.35f) = lerp(this, Color.Black, f)
private fun Color.lighter(f: Float = 0.5f) = lerp(this, Color.White, f)

fun DrawScope.drawSupplement(look: Look, withBackground: Boolean = true) {
    val c = look.color
    val m = minOf(size.width, size.height)
    val cx = size.width / 2f
    val cy = size.height / 2f
    if (withBackground) {
        drawRect(Brush.radialGradient(listOf(c.lighter(0.82f), c.lighter(0.55f)), Offset(cx, cy * 0.8f), m * 0.9f))
    }
    // Sombra suave en el "suelo".
    drawOval(Color.Black.copy(alpha = 0.10f), Offset(cx - m * 0.36f, cy + m * 0.26f), Size(m * 0.72f, m * 0.10f))
    when (look.form) {
        Form.CAPSULA -> {
            capsule(c, Offset(cx - m * 0.06f, cy - m * 0.04f), m, -32f)
            capsule(c, Offset(cx + m * 0.10f, cy + m * 0.14f), m * 0.85f, 18f)
        }
        Form.PERLA -> {
            softgel(c, Offset(cx - m * 0.13f, cy - m * 0.06f), m, -25f)
            softgel(c, Offset(cx + m * 0.16f, cy + m * 0.10f), m * 0.9f, 30f)
            softgel(c, Offset(cx - m * 0.05f, cy + m * 0.20f), m * 0.75f, 80f)
        }
        Form.COMPRIMIDO -> {
            tablet(c, Offset(cx - m * 0.14f, cy - m * 0.02f), m * 0.17f)
            tablet(c, Offset(cx + m * 0.17f, cy + m * 0.12f), m * 0.15f)
            tablet(c.lighter(0.15f), Offset(cx + m * 0.06f, cy - m * 0.20f), m * 0.12f)
        }
        Form.POLVO -> tub(c, cx, cy, m)
        Form.GOTAS -> dropper(c, cx, cy, m)
        Form.PLANTA -> plant(c, cx, cy, m)
    }
}

private val LEAF_GREEN = Color(0xFF4E8F3A)

private fun DrawScope.leaf(base: Offset, length: Float, angle: Float, color: Color) {
    val w = length * 0.42f
    rotate(angle, base) {
        val tip = Offset(base.x, base.y - length)
        val shape = Path().apply {
            moveTo(base.x, base.y)
            cubicTo(base.x + w, base.y - length * 0.25f, base.x + w * 0.8f, base.y - length * 0.8f, tip.x, tip.y)
            cubicTo(base.x - w * 0.8f, base.y - length * 0.8f, base.x - w, base.y - length * 0.25f, base.x, base.y)
            close()
        }
        drawPath(shape, Brush.linearGradient(listOf(color.lighter(0.25f), color.darker(0.2f)), base, tip))
        drawLine(color.darker(0.4f).copy(alpha = 0.6f), base, Offset(tip.x, tip.y + length * 0.1f), length * 0.035f)
        drawPath(shape, color.darker(0.45f).copy(alpha = 0.35f), style = Stroke(length * 0.02f))
    }
}

private fun DrawScope.plant(c: Color, cx: Float, cy: Float, m: Float) {
    val base = Offset(cx, cy + m * 0.30f)
    val stemTop = Offset(cx + m * 0.02f, cy - m * 0.30f)
    val stem = Path().apply {
        moveTo(base.x, base.y)
        quadraticBezierTo(cx - m * 0.08f, cy, stemTop.x, stemTop.y)
    }
    drawPath(stem, LEAF_GREEN.darker(0.3f), style = Stroke(m * 0.022f))
    leaf(Offset(cx - m * 0.03f, cy + m * 0.16f), m * 0.30f, -58f, LEAF_GREEN)
    leaf(Offset(cx - m * 0.04f, cy + m * 0.04f), m * 0.28f, 52f, LEAF_GREEN.lighter(0.1f))
    leaf(Offset(cx - m * 0.02f, cy - m * 0.10f), m * 0.24f, -40f, LEAF_GREEN.darker(0.05f))
    leaf(Offset(cx, cy - m * 0.20f), m * 0.20f, 35f, LEAF_GREEN.lighter(0.2f))
    // Flor o fruto con el color de la planta.
    for (k in 0 until 6) {
        rotate(k * 60f, stemTop) {
            drawOval(c.lighter(0.15f), Offset(stemTop.x - m * 0.035f, stemTop.y - m * 0.11f), Size(m * 0.07f, m * 0.10f))
        }
    }
    drawCircle(c.darker(0.35f), m * 0.035f, stemTop)
    // Cápsula al lado: es un suplemento.
    capsule(c, Offset(cx + m * 0.24f, cy + m * 0.26f), m * 0.6f, -20f)
}

private fun DrawScope.capsule(c: Color, center: Offset, m: Float, angle: Float) {
    val w = m * 0.52f
    val h = m * 0.20f
    val tl = Offset(center.x - w / 2, center.y - h / 2)
    val r = CornerRadius(h / 2)
    rotate(angle, center) {
        drawRoundRect(Color(0xFFF7F7F2), tl, Size(w, h), r)
        clipRect(tl.x, tl.y, center.x, tl.y + h) {
            drawRoundRect(Brush.verticalGradient(listOf(c.lighter(0.2f), c, c.darker(0.25f)), tl.y, tl.y + h), tl, Size(w, h), r)
        }
        drawLine(Color.Black.copy(alpha = 0.15f), Offset(center.x, tl.y + 2), Offset(center.x, tl.y + h - 2), 2f)
        drawRoundRect(Color.White.copy(alpha = 0.55f), Offset(tl.x + h * 0.4f, tl.y + h * 0.18f), Size(w - h * 0.8f, h * 0.16f), CornerRadius(h * 0.1f))
        drawRoundRect(c.darker(0.4f).copy(alpha = 0.5f), tl, Size(w, h), r, style = Stroke(m * 0.008f))
    }
}

private fun DrawScope.softgel(c: Color, center: Offset, m: Float, angle: Float) {
    val w = m * 0.34f
    val h = m * 0.19f
    val tl = Offset(center.x - w / 2, center.y - h / 2)
    rotate(angle, center) {
        drawOval(Brush.radialGradient(listOf(c.lighter(0.35f), c, c.darker(0.3f)), Offset(center.x - w * 0.15f, center.y - h * 0.2f), w * 0.6f), tl, Size(w, h))
        drawOval(Color.White.copy(alpha = 0.65f), Offset(tl.x + w * 0.18f, tl.y + h * 0.16f), Size(w * 0.34f, h * 0.22f))
        drawOval(c.darker(0.45f).copy(alpha = 0.35f), tl, Size(w, h), style = Stroke(m * 0.006f))
    }
}

private fun DrawScope.tablet(c: Color, center: Offset, r: Float) {
    drawCircle(c.darker(0.25f), r, center + Offset(0f, r * 0.12f))
    drawCircle(Brush.radialGradient(listOf(c.lighter(0.55f), c.lighter(0.15f)), center - Offset(r * 0.3f, r * 0.3f), r * 1.4f), r, center)
    drawLine(c.darker(0.3f).copy(alpha = 0.5f), center - Offset(r * 0.65f, 0f), center + Offset(r * 0.65f, 0f), r * 0.08f)
    drawCircle(Color.White.copy(alpha = 0.5f), r * 0.18f, center - Offset(r * 0.45f, r * 0.45f))
}

private fun DrawScope.tub(c: Color, cx: Float, cy: Float, m: Float) {
    val w = m * 0.42f
    val h = m * 0.46f
    val top = cy - h * 0.5f
    val left = cx - w / 2
    // Cuerpo
    drawRoundRect(Brush.horizontalGradient(listOf(c.darker(0.15f), c.lighter(0.15f), c.darker(0.3f)), left, left + w),
        Offset(left, top + h * 0.12f), Size(w, h * 0.88f), CornerRadius(m * 0.03f))
    // Tapa
    drawRoundRect(Brush.horizontalGradient(listOf(Color(0xFF2B2B2B), Color(0xFF555555), Color(0xFF1E1E1E)), left, left + w),
        Offset(left - w * 0.03f, top), Size(w * 1.06f, h * 0.16f), CornerRadius(m * 0.02f))
    // Etiqueta
    drawRoundRect(Color.White.copy(alpha = 0.92f), Offset(left + w * 0.1f, top + h * 0.38f), Size(w * 0.8f, h * 0.36f), CornerRadius(m * 0.015f))
    drawRoundRect(c, Offset(left + w * 0.18f, top + h * 0.46f), Size(w * 0.64f, h * 0.07f), CornerRadius(m * 0.01f))
    drawRoundRect(c.lighter(0.5f), Offset(left + w * 0.18f, top + h * 0.58f), Size(w * 0.44f, h * 0.05f), CornerRadius(m * 0.01f))
    // Brillo
    drawRoundRect(Color.White.copy(alpha = 0.25f), Offset(left + w * 0.06f, top + h * 0.18f), Size(w * 0.06f, h * 0.75f), CornerRadius(m * 0.02f))
    // Cacito con polvo
    val sx = cx + w * 0.62f
    val sy = cy + h * 0.38f
    drawOval(c.darker(0.2f), Offset(sx - m * 0.09f, sy - m * 0.03f), Size(m * 0.18f, m * 0.08f))
    drawOval(Color(0xFFFAFAF5), Offset(sx - m * 0.08f, sy - m * 0.05f), Size(m * 0.16f, m * 0.07f))
    drawLine(c.darker(0.2f), Offset(sx + m * 0.07f, sy), Offset(sx + m * 0.17f, sy - m * 0.05f), m * 0.02f)
}

private fun DrawScope.dropper(c: Color, cx: Float, cy: Float, m: Float) {
    val w = m * 0.26f
    val h = m * 0.36f
    val left = cx - w / 2 - m * 0.06f
    val top = cy - h * 0.25f
    val glass = c.darker(0.35f)
    drawRoundRect(Brush.horizontalGradient(listOf(glass.darker(0.2f), glass.lighter(0.2f), glass.darker(0.3f)), left, left + w),
        Offset(left, top), Size(w, h), CornerRadius(m * 0.05f))
    drawRoundRect(Color.White.copy(alpha = 0.9f), Offset(left + w * 0.12f, top + h * 0.35f), Size(w * 0.76f, h * 0.36f), CornerRadius(m * 0.01f))
    drawRoundRect(c, Offset(left + w * 0.22f, top + h * 0.45f), Size(w * 0.56f, h * 0.08f))
    // Cuello y pipeta
    drawRect(Color(0xFF333333), Offset(left + w * 0.2f, top - h * 0.12f), Size(w * 0.6f, h * 0.13f))
    drawRoundRect(Color(0xFF1F1F1F), Offset(left + w * 0.28f, top - h * 0.40f), Size(w * 0.44f, h * 0.30f), CornerRadius(m * 0.05f))
    drawRoundRect(Color.White.copy(alpha = 0.22f), Offset(left + w * 0.08f, top + h * 0.06f), Size(w * 0.08f, h * 0.85f), CornerRadius(m * 0.02f))
    // Gota
    val dx = cx + m * 0.2f
    val dy = cy + m * 0.04f
    val r = m * 0.07f
    val drop = Path().apply {
        moveTo(dx, dy - r * 2.1f)
        cubicTo(dx + r * 0.4f, dy - r * 1.1f, dx + r, dy - r * 0.6f, dx + r, dy)
        cubicTo(dx + r, dy + r * 1.2f, dx - r, dy + r * 1.2f, dx - r, dy)
        cubicTo(dx - r, dy - r * 0.6f, dx - r * 0.4f, dy - r * 1.1f, dx, dy - r * 2.1f)
        close()
    }
    drawPath(drop, Brush.verticalGradient(listOf(c.lighter(0.3f), c), dy - r * 2f, dy + r))
    drawCircle(Color.White.copy(alpha = 0.6f), r * 0.22f, Offset(dx - r * 0.35f, dy - r * 0.2f))
}
