package com.example.custompiechart.data

import android.graphics.Paint
import android.graphics.PointF

data class Pie(
    var value: Double,
    var startAngle: Float,
    var sweepAngle: Float,
    var indicatorCircleLocation: PointF,
    val paint: Paint
)
