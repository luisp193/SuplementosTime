package com.suplementostime.app.ui

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.sin

private const val SPLASH_MS = 2000

private val splashLooks = listOf(
    Look(Form.POLVO, Color(0xFF4F8FD8)),
    Look(Form.PERLA, Color(0xFFE09A2C)),
    Look(Form.CAPSULA, Color(0xFF26A69A)),
    Look(Form.COMPRIMIDO, Color(0xFFFF8F00)),
)

/** Animación de ~2 s: imágenes de suplementos que se funden una en otra, con un sonido muy suave. */
@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val ctx = LocalContext.current
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        SoftChime.play(ctx)
        progress.animateTo(1f, tween(SPLASH_MS, easing = LinearEasing))
        onFinished()
    }
    val p = progress.value
    val n = splashLooks.size
    val fadeIn = (p * 8f).coerceIn(0f, 1f)
    val fadeOut = ((1f - p) * 8f).coerceIn(0f, 1f)

    Box(
        Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF0E3B2C), Color(0xFF1B7F5C)))),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(240.dp), contentAlignment = Alignment.Center) {
                splashLooks.forEachIndexed { i, look ->
                    val a = (1.4f - abs(p * n - (i + 0.5f)) * 1.6f).coerceIn(0f, 1f) * fadeIn * fadeOut
                    if (a > 0f) {
                        Canvas(Modifier.fillMaxSize().alpha(a).scale(0.88f + 0.12f * a)) {
                            drawSupplement(look, withBackground = false)
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(
                "Suplementos Time", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(((p - 0.1f) * 3f).coerceIn(0f, 1f) * fadeOut),
            )
        }
    }
}

/** Acorde suave generado por código (sin archivos de audio). No suena si el móvil está en silencio o vibración. */
object SoftChime {
    fun play(context: Context) {
        val am = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
        if (am != null && am.ringerMode != AudioManager.RINGER_MODE_NORMAL) return
        Thread {
            try {
                val rate = 44100
                val seconds = 1.9
                val count = (rate * seconds).toInt()
                val notes = doubleArrayOf(523.25, 659.25, 783.99) // Do, Mi, Sol
                val delays = doubleArrayOf(0.0, 0.12, 0.24)
                val pcm = ShortArray(count)
                for (i in 0 until count) {
                    val t = i / rate.toDouble()
                    var v = 0.0
                    for (k in notes.indices) {
                        val tk = t - delays[k]
                        if (tk < 0) continue
                        val attack = (tk / 0.25).coerceAtMost(1.0)
                        val env = attack * exp(-2.4 * tk)
                        v += env * (sin(2 * PI * notes[k] * tk) + 0.25 * sin(4 * PI * notes[k] * tk))
                    }
                    val tail = ((seconds - t) / 0.15).coerceIn(0.0, 1.0)
                    pcm[i] = (v * tail * 0.09 * Short.MAX_VALUE).toInt().coerceIn(-32767, 32767).toShort()
                }
                val track = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(rate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .setBufferSizeInBytes(count * 2)
                    .build()
                track.write(pcm, 0, count)
                track.play()
                Thread.sleep((seconds * 1000).toLong() + 200)
                track.release()
            } catch (_: Exception) {
                // El sonido es decorativo: si falla, la app sigue igual.
            }
        }.start()
    }
}
