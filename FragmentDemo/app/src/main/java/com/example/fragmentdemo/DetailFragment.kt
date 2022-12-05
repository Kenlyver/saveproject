package com.example.fragmentdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragmentdemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment(),ShowFragment.OnInputSelected {
    lateinit var binding: FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailBinding.inflate(inflater,container,false)
        val bundle = this.arguments
        if (bundle != null) {
            val FullName = bundle.getString("FullName")
            val CardNumber = bundle.getString("NumberAccount")
            val Date = bundle.getString("Date")
            binding.txtFullName.text=FullName
            binding.txtNumberCard.text=CardNumber
            binding.txtDate.text=Date
        }
        binding.btnClose.setOnClickListener {
            val activity=binding.root.getContext() as AppCompatActivity
            val listFragment = ListFragment()
            listFragment.setArguments(bundle)
            activity.supportFragmentManager.beginTransaction().replace(R.id.listFragment,listFragment).addToBackStack(null).commit()
        }
        binding.btnUpdate.setOnClickListener {
            val activity=binding.root.getContext() as AppCompatActivity
            val dialog=ShowFragment()
            dialog.setTargetFragment(this,1)
            dialog.show(activity.supportFragmentManager,"AAA")
        }
        return binding.root
    }

    override fun sendInput(FullName: String, CardNumber: String, Date: String) {
        binding.txtFullName.text = FullName
        binding.txtNumberCard.text = CardNumber
        binding.txtDate.text = Date
    }
}