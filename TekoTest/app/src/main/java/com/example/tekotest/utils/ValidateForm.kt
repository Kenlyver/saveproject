package com.example.tekotest.utils

import android.content.Context
import com.example.tekotest.R
import com.google.android.material.textfield.TextInputLayout

class ValidateForm(private val context: Context) {
    var isValidate = true
        private set

    fun validateProductName(textInputLayout: TextInputLayout) {
        val name = textInputLayout.editText?.text.toString()
        if (name == "") {
            textInputLayout.error = context.getString(R.string.name_null)
            isValidate = false
        } else if (name.length > 50) {
            textInputLayout.error = context.getString(R.string.name_length)
            isValidate = false
        } else textInputLayout.helperText = context.getString(R.string.valid_success)
    }

    fun validateSKU(textInputLayout: TextInputLayout) {
        val sku = textInputLayout.editText?.text.toString()
        if (sku == "") {
            textInputLayout.error = context.getString(R.string.sku_null)
            isValidate = false
        } else if (sku.length > 20) {
            textInputLayout.error = context.getString(R.string.sku_length)
            isValidate = false
        } else textInputLayout.helperText = context.getString(R.string.valid_success)
    }
}