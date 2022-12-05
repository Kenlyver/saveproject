package com.example.custompiechart

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.custompiechart.data.PieData
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class PieChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var data: PieData? = null
    private val oval = RectF()

    fun setData(data: PieData) {
        this.data = data
        setPieSliceDimensions()
        invalidate()
    }

    private fun setPieSliceDimensions() {
        var lastAngle = 0f
        data?.pieSlices?.forEach {
            it.value.startAngle = lastAngle
            it.value.sweepAngle = (((it.value.value / data?.totalValue!!)) * 360f).toFloat()
            lastAngle += it.value.sweepAngle

        }
    }

    private fun setCircleBounds(
        top: Float = 0f, bottom: Float = layoutParams.height.toFloat(),
        left: Float = (width / 2) - (layoutParams.height / 2).toFloat(),
        right: Float = (width / 2) + (layoutParams.height / 2).toFloat()
    ) {
        oval.top = top
        oval.bottom = bottom
        oval.left = left
        oval.right = right
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setCircleBounds()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val textPaint = Paint()
        textPaint.setTextSize(18 * Resources.getSystem().getDisplayMetrics().densityDpi / 160f)
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
        textPaint.setColor(Color.WHITE)
        val bounds = Rect()

        data?.pieSlices?.let { slices ->
            slices.forEach {
                textPaint.getTextBounds(
                    it.value.value.toString(),
                    0,
                    it.value.value.toString().length,
                    bounds
                )
                val medianAngle =
                    (it.value.startAngle + (it.value.sweepAngle / 2)) * PI.toFloat() / 180f
                val x = oval.centerX()
                val y = oval.centerY()
                val r = oval.centerY() * 0.5f
                canvas?.apply {
                    drawArc(oval, it.value.startAngle, it.value.sweepAngle, true, it.value.paint)
                    drawText(
                        "${it.value.value.toInt()}%",
                        x + (r * cos(medianAngle)),
                        y + (r * sin(medianAngle)),
                        textPaint
                    )
                }
            }
        }
    }
}