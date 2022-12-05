package com.example.customviewdemo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class CustView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    var buttonTapped: (() -> Unit)? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_view, this, false)
        val set = ConstraintSet()
        addView(view)

        set.clone(this)
        set.match(view, this)
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            buttonTapped?.invoke()
        }
    }

}

fun ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}