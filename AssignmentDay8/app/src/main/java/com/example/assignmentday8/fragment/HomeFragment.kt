package com.example.assignmentday8.fragment

import android.os.Build
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.assignmentday8.databinding.FragmentHomeBinding
import com.example.assignmentday8.model.dbInfo.Info
import com.example.assignmentday8.viewModel.InfoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModelInfo: InfoViewModel by viewModels()
    private lateinit var price: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
//        viewModelInfo.insertWallet(Wallet(1,"100000","20"))
        viewModelInfo.wallet.observe(viewLifecycleOwner, Observer {
            binding.txtVND.setText(it.vnd)
            binding.txtUSD.setText(it.usd)
        })
        viewModelInfo.cake.observe(viewLifecycleOwner, Observer {
            binding.txtNumberOfCake.setText(it.Amount.toString())
            price = it.Price
        })
        binding.btnAdd.setOnClickListener {
            viewModelInfo.upCake()
            viewModelInfo.numberCake.observe(viewLifecycleOwner, Observer {
                binding.edtSaleCake.setText(it.toString())
            })
        }
        binding.btnMinus.setOnClickListener {
            viewModelInfo.downCake()
            viewModelInfo.numberCake.observe(viewLifecycleOwner, Observer {
                binding.edtSaleCake.setText(it.toString())
            })
        }
        binding.txtPaymentCurrency.setOnClickListener {
            val listItems = arrayOf("VND", "USD")
            val builder = AlertDialog.Builder(requireContext())
            var save = 0
            builder.apply {
                setTitle("Select payment currency")
                setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                    save = i
                }
                setPositiveButton("OK") { dialogInterface, i ->
                    binding.txtPaymentCurrency.text = listItems[save]
                    viewModelInfo.numberCake.observe(viewLifecycleOwner, Observer {
                        if (listItems[save].equals("VND")) {
                            val bill = price.toInt() * it.toInt()
                            binding.txtBill.text = bill.toString()
                        } else {
                            val bill: Float = ((price.toFloat() * it.toFloat()) / 23390)
                            binding.txtBill.text = bill.toString()
                        }
                    })
                }
            }
            val mDialog = builder.create()
            mDialog.show()
        }
        binding.btnSale.setOnClickListener {
            val amount = binding.edtSaleCake.text.toString()
            val currentCake = binding.txtNumberOfCake.text.toString()
            val holderName = binding.edtHolderName.text.toString()
            val currency = binding.txtPaymentCurrency.text.toString()
            val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDateTime.now()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")
            val formattedTime = currentTime.format(formatter)
            if (amount.toInt() == 0 || holderName == null || currency == null || currentTime == null) {
                Toast.makeText(context, "Something is not fill", Toast.LENGTH_SHORT).show()
            } else {
                if (amount.toInt() > currentCake.toInt()) {
                    Toast.makeText(context, "Cake if empty", Toast.LENGTH_SHORT).show()
                } else {
                    if (currency.equals("VND")) {
                        val bill = binding.txtBill.text.toString()
                        val wallet = binding.txtVND.text.toString()
                        val temp = (wallet.toInt() + bill.toInt())
                        viewModelInfo.updateWalletVND(temp.toString())
                    } else {
                        val bill = binding.txtBill.text.toString()
                        val wallet = binding.txtUSD.text.toString()
                        val temp = (wallet.toFloat() + bill.toFloat())
                        viewModelInfo.updateWalletUSD(temp.toString())
                    }
                    val temp = currentCake.toInt() - amount.toInt()
                    val info = Info(
                        null,
                        holderName = holderName,
                        amount = amount.toInt(),
                        transactionType = "SALE",
                        currency = currency,
                        dateTime = formattedTime
                    )
                    viewModelInfo.resetCake()
                    binding.edtHolderName.setText("")
                    binding.txtBill.setText("")
                    viewModelInfo.insertInfo(info)
                    viewModelInfo.updateAmoutCake(temp)
                }
            }
        }
        return binding.root
    }
}