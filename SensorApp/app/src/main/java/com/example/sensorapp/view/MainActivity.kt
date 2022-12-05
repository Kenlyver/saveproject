package com.example.sensorapp.view

import android.content.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import com.example.clientcar.AIDLConfig
import com.example.clientcar.model.DataTPMS
import com.example.sensorapp.Constant.ACTION_RECEIVER
import com.example.sensorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var dataService: AIDLConfig
    private lateinit var dataGet: List<DataTPMS>
    private var checkConnect = false
    private val activityResultLauncher by lazy {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (!Settings.canDrawOverlays(this)) {
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                    data = Uri.parse("package:$packageName")
                    startActivity(this)
                }
            }
        }
    }
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.run {
                if (action == ACTION_RECEIVER) {
                    val list = getParcelableArrayListExtra<Parcelable>("DATA")
                    dataGet = list as List<DataTPMS>
                }
            }
        }

    }
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            dataService = AIDLConfig.Stub.asInterface(service)
            checkConnect = true
            dataService.sendAction(ACTION_RECEIVER)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            checkConnect = false
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnSetTPMS.setOnClickListener {
            setDataTPMS()
        }
        if (!Settings.canDrawOverlays(this)) {
            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                data = Uri.parse("package:$packageName")
                activityResultLauncher.launch(this)
            }
        }
        setContentView(binding.root)
    }

    private fun setDataTPMS() {
       binding.apply {
            val result =DataTPMS(
                topLeftPressure = topLeftPressure.text.toString().toFloat(),
                bottomLeftPressure = bottomLeftPressure.text.toString().toFloat(),
                topRightPressure = topRightPressure.text.toString().toFloat(),
                bottomRightPressure = bottomRightPressure.text.toString().toFloat(),
                topLeftTemperature = topLeftTemparature.text.toString().toFloat(),
                bottomLeftTemperature = bottomLeftTemparature.text.toString().toFloat(),
                topRightTemperature = topRightTemparature.text.toString().toFloat(),
                bottomRightTemperature = bottomRightTemparature.text.toString().toFloat()
            )
           dataService.insertData(result)
       }
    }
    override fun onStart() {
        super.onStart()
        registerReceiver(receiver, IntentFilter(ACTION_RECEIVER))
        Intent("com.example.clientcar.RemoteService").apply {
            setPackage("com.example.clientcar")
            bindService(this, connection, BIND_AUTO_CREATE)
        }
    }
}