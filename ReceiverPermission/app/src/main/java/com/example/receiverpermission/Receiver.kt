package com.example.receiverpermission

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var resultCode: Int = getResultCode()
        var resultData = getResultData()
        var resultExtras = getResultExtras(true)
        var stringExtras = resultExtras.getString("stringExtra")

        resultCode++
        stringExtras = stringExtras.plus("->Received")

        var textNoti = "Received\n resultCode:${resultCode}\n " +
                "resultData: ${resultData}\n stringExtra: ${stringExtras}"
        Toast.makeText(context, textNoti, Toast.LENGTH_SHORT).show()
        resultData = "Received"
        resultExtras.putString("stringExtra", stringExtras)
        setResult(resultCode, resultData, resultExtras)

    }
}