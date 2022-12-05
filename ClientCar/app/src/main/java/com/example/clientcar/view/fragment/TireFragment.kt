package com.example.clientcar.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.clientcar.R
import com.example.clientcar.databinding.FragmentTireBinding
import com.example.clientcar.model.DataTPMS
import com.example.sensorapp.viewmodel.TPMSViewModel

class TireFragment : Fragment() {
    private lateinit var binding: FragmentTireBinding
    private val viewModel: TPMSViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTireBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.tpmsData.observe(viewLifecycleOwner) {
            Log.d("test", it.toString())
            val tlPress = it[0].topLeftPressure.toString().toFloat()
            val trPress = it[0].topRightPressure.toString().toFloat()
            val blPress = it[0].bottomLeftPressure.toString().toFloat()
            val brPress = it[0].bottomRightPressure.toString().toFloat()
            val tlTem = it[0].topLeftTemperature.toString().toFloat()
            val trTem = it[0].topRightTemperature.toString().toFloat()
            val blTem = it[0].bottomLeftTemperature.toString().toFloat()
            val brTem = it[0].bottomRightTemperature.toString().toFloat()
            val result = DataTPMS(
                topLeftPressure = tlPress,
                bottomLeftPressure = blPress,
                topRightPressure = trPress,
                bottomRightPressure = brPress,
                topLeftTemperature = tlTem,
                bottomLeftTemperature = blTem,
                topRightTemperature = trTem,
                bottomRightTemperature = brTem
            )
            binding.data = result
            if (tlPress < 5 || tlTem < 30) {
                binding.txtTopLeftStatus.isVisible = true
                binding.layoutTopLeft.setBackgroundResource(R.drawable.custom_border_red)
            }
            if (trPress < 5 || trTem < 30) {
                binding.txtTopRightStatus.isVisible = true
                binding.layoutTopRight.setBackgroundResource(R.drawable.custom_border_red)
            }
            if (blPress < 5 || blTem < 30) {
                binding.txtBottomLeftStatus.isVisible = true
                binding.layoutBottomLeft.setBackgroundResource(R.drawable.custom_border_red)
            }
            if (brPress< 5 || brTem < 30) {
                binding.txtBottomRightStatus.isVisible = true
                binding.layoutBottomRight.setBackgroundResource(R.drawable.custom_border_red)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}