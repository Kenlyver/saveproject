package com.example.myservicedemo

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myservicedemo.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {
    lateinit var binding: FragmentPlayBinding
    private lateinit var serviceIntent: Intent
    private var result = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater, container, false)
        binding.btnResult.setOnClickListener {
            val a = binding.edtA.text.toString()
            val b = binding.edtB.text.toString()
            val cal = binding.edtCal.text.toString()
            CalculatorService.startService(context!!, a.toInt(), cal, b.toInt())
            serviceIntent = Intent(context, CalculatorService::class.java)
            activity?.registerReceiver(updateResult, IntentFilter(CalculatorService.CALCULATOR))
        }
        return binding.root
    }

    private val updateResult: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            result = intent.getIntExtra(CalculatorService.DATA, 0)
            binding.txtResult.text = result.toString()
        }
    }

}