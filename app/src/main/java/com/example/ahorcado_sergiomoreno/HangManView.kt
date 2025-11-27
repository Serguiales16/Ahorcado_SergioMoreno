package com.example.ahorcado_sergiomoreno

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.View


class HangmanView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // 👉 AQUÍ pondrás tus bitmaps
    private val cabeza = BitmapFactory.decodeResource(
        resources,
        /* TODO: R.drawable.cabeza */
    )

    private val torso = BitmapFactory.decodeResource(
        resources,
        /* TODO: R.drawable.torso */
    )

    private val brazoIzq = BitmapFactory.decodeResource(
        resources,
        /* TODO: R.drawable.brazo_izq */
    )

    private val brazoDer = BitmapFactory.decodeResource(
        resources,
        /* TODO: R.drawable.brazo_der */
    )

    private val piernaIzq = BitmapFactory.decodeResource(
        resources,
        /* TODO: R.drawable.pierna_izq */
    )

    private val piernaDer = BitmapFactory.decodeResource(
        resources,
        /* TODO: R.drawable.pierna_der */
    )

    private var fallos = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 🔴 Punto de referencia: centro de la soga
        val cx = width / 2f
        val cy = height * 0.25f   // ajusta si hace falta

        if (fallos >= 1) drawCentered(canvas, cabeza, cx, cy)
        if (fallos >= 2) drawCentered(canvas, torso, cx, cy + 80f)
        if (fallos >= 3) drawCentered(canvas, brazoIzq, cx - 40f, cy + 90f)
        if (fallos >= 4) drawCentered(canvas, brazoDer, cx + 40f, cy + 90f)
        if (fallos >= 5) drawCentered(canvas, piernaIzq, cx - 25f, cy + 170f)
        if (fallos >= 6) drawCentered(canvas, piernaDer, cx + 25f, cy + 170f)
    }

    private fun drawCentered(
        canvas: Canvas,
        bmp: Bitmap,
        x: Float,
        y: Float
    ) {
        canvas.drawBitmap(
            bmp,
            x - bmp.width / 2,
            y,
            null
        )
    }

    fun fallo() {
        if (fallos < 6) {
            fallos++
            invalidate() // 🔥 vuelve a dibujar
        }
    }
}
