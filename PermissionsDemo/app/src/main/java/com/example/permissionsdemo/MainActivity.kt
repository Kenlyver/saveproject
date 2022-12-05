package com.example.permissionsdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Telephony
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissionsdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val PICTURE_ID = 123
        private const val SMS_REQUEST_CODE = 1
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCamera.setOnClickListener {
            if (checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)) {
                val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(camera_intent, PICTURE_ID)
            }

        }
        binding.btnReadSMS.setOnClickListener {
            if (checkPermission(android.Manifest.permission.READ_SMS, SMS_REQUEST_CODE)) {
                readSms()
            }
        }
    }

    private fun checkPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) ==
            PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
            return true
        } else {
            Toast.makeText(this@MainActivity, "Permission is granted", Toast.LENGTH_SHORT).show()
            return true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Camera Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@MainActivity, "Camera Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICTURE_ID) {
            val photo: Bitmap = data?.getExtras()
                ?.get("data") as Bitmap
            binding.imgPhoto.setImageBitmap(photo)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun readSms() {
        val numberCol = Telephony.TextBasedSmsColumns.ADDRESS
        val textCol = Telephony.TextBasedSmsColumns.BODY
        val typeCol = Telephony.TextBasedSmsColumns.TYPE // 1 - Inbox, 2 - Sent

        val projection = arrayOf(numberCol, textCol, typeCol)

        val cursor = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            projection, null, null, null
        )

        val numberColIdx = cursor!!.getColumnIndex(numberCol)
        val textColIdx = cursor.getColumnIndex(textCol)
        val typeColIdx = cursor.getColumnIndex(typeCol)
        val list = ArrayList<String>()
        while (cursor.moveToNext()) {
            val number = cursor.getString(numberColIdx)
            val text = cursor.getString(textColIdx)
            val type = cursor.getString(typeColIdx)
            list += number.toString().plus(":").plus(text.toString())
            binding.txtSMS.text = list.toString()
            Log.d("MY_APP", "$number $text $type")
            Log.d("LIST", list.toString())
        }

        cursor.close()
    }

    fun sendBroadcast(v: View) {
        intent = Intent("com.kenlyver.EXAMPLE_APP")
        intent.setPackage("com.example.receiverpermission")
        val extras = Bundle()
        extras.putString("stringExtra", "Start")

        sendOrderedBroadcast(
            intent, "com.example.CUSTOM_PERMISSION", SenderRec(),
            null, 0, "Start", extras
        )
    }
}