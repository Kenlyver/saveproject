package com.example.servicedownload

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import java.io.*
import java.net.URL


class DownService : IntentService("BackgroundIntentService") {
    private val LOG_TAG = "BackgroundIntentService"

    companion object {
        const val FILENAME = "filename"
        const val URL = "url"
        const val FILEPATH = "filepath"
        const val RESULT = "result"
        const val NOTIFICATION = "service receiver"
        var result = Activity.RESULT_CANCELED

    }

    override fun onHandleIntent(intent: Intent?) {
        val urlPath = intent!!.getStringExtra(URL)
        val fileName = intent.getStringExtra(FILENAME)
        val output = File(
            getExternalFilesDir("/")?.getAbsolutePath(),
            fileName
        )
        if (output.exists()) {
            output.delete()
        }

        var stream: InputStream? = null
        var fos: FileOutputStream? = null
        try {
            val url = URL(urlPath)
            stream = url.openConnection().getInputStream()
            val reader = InputStreamReader(stream)
            fos = FileOutputStream(output.getPath())
            var next = -1
            while (reader.read().also { next = it } != -1) {
                fos.write(next)
            }
            result = Activity.RESULT_OK
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        getResults(output.getAbsolutePath(), result)
    }

    private fun getResults(outputPath: String, result: Int) {
        val intent = Intent(NOTIFICATION)
        intent.putExtra(FILEPATH, outputPath)
        intent.putExtra(RESULT, result)
        sendBroadcast(intent)
    }
}