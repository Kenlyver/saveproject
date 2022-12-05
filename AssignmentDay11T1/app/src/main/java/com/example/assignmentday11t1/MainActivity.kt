package com.example.assignmentday11t1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentday11t1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var intentServer: Intent

    private lateinit var intentClient: Intent

    private var mClient: Messenger? = null
    private var mServer: Messenger? = null

    private var isStartServer = false
    private var isStartClient = false
    private var isCommunicate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intentServer =
            Intent(this, ServerServices::class.java)
        intentClient = Intent(this, ClientServices::class.java)
        binding.apply {
            btnServer.setOnClickListener {
                handleServer()
            }
            btnConnect.setOnClickListener {
                handleCommunication()
            }
            btnClient.setOnClickListener {
                handleClient()
            }
        }
    }

    private fun handleServer() {
        if (isStartServer) {
            stopServer()
        } else {
            bindService(intentServer, connectionServer, Context.BIND_AUTO_CREATE)
            binding.btnServer.text = getString(R.string.stop_server)
        }
        isStartServer = !isStartServer
    }

    private fun handleClient() {
        if (isStartClient) {
            stopClient()
        } else {
            bindService(intentClient, connectionClient, Context.BIND_AUTO_CREATE)
            binding.btnClient.text = getString(R.string.stop_client)
        }
        isStartClient = !isStartClient
    }

    private fun stopServer() {
        val message = Message.obtain().apply {
            what = Constant.STOP_SERVER
        }
        mServer?.send(message)
        unbindService(connectionServer)
        stopService(intentServer)
        binding.btnServer.text = getString(R.string.start_server)
    }

    private fun stopClient() {
        val message = Message.obtain().apply {
            what = Constant.STOP_CLIENT
        }
        mClient?.send(message)
        unbindService(connectionClient)
        stopService(intentClient)
        binding.btnClient.text = getString(R.string.start_client)
    }

    private fun handleCommunication() {
        if (isCommunicate) {
            if (isStartServer) stopServer()
            if (isStartClient) stopClient()
            binding.btnConnect.text = getString(R.string.start_connect)
        } else {
            if (mClient != null && mServer != null) {
                val msg = Message.obtain().apply {
                    what = Constant.START_COMMUNICATE
                    replyTo = mServer
                }
                mClient!!.send(msg)
            } else {
                Toast.makeText(
                    this,
                    "Start client, server and start again",
                    Toast.LENGTH_LONG
                ).show()
            }
            binding.btnConnect.text = getString(R.string.stop_connect)
        }
        isCommunicate = !isCommunicate
    }

    private val connectionServer = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mServer = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mServer = null
        }

    }

    private val connectionClient = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mClient = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mClient = null
        }

    }
}