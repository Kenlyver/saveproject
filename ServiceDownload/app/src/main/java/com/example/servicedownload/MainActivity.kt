package com.example.servicedownload

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.servicedownload.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDownload.setOnClickListener {
            if (binding.edtLink.text != null) {
                val url = binding.edtLink.text.toString()
                val intent = Intent(this, DownService::class.java)
                intent.putExtra(DownService.FILENAME, "index.html")
                intent.putExtra(
                    DownService.URL,
                    url
                )
                startService(intent)
                binding.txtStatus.setText("Service started")
            } else {
                Toast.makeText(this, "Enter the link to download", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        registerReceiver(
            receiver, IntentFilter(
                DownService.NOTIFICATION
            )
        );
        super.onResume()
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val bundle = intent.extras
            if (bundle != null) {
                val string = bundle.getString(DownService.FILEPATH)
                val resultCode = bundle.getInt(DownService.RESULT)
                if (resultCode == RESULT_OK) {
                    Toast.makeText(
                        this@MainActivity,
                        "Download complete. Download URI: $string",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.txtStatus.setText("Download done")
                } else {
                    Toast.makeText(
                        this@MainActivity, "Download failed",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.txtStatus.setText("Download failed")
                }
            }
        }
    }
}