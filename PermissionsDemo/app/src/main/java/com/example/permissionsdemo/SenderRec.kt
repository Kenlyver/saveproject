package com.example.permissionsdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class SenderRec : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var resultCode = resultCode
        var resultData = resultData
        val resultExtras = getResultExtras(true)
        var stringExtra = resultExtras.getString("stringExtra")
        resultCode++
        stringExtra += "->SenderReceiver"
        val toastText = """
             SenderReceiver
             resultCode: $resultCode
             resultData: $resultData
             stringExtra: $stringExtra
             """.trimIndent()
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
        resultData = "SenderReceiver"
        resultExtras.putString("stringExtra", stringExtra)
        setResult(resultCode, resultData, resultExtras)
    }
}