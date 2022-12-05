package com.example.fragmentdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.databinding.FragmentShowBinding


class ShowFragment : androidx.fragment.app.DialogFragment() {


    interface OnInputSelected {
        fun sendInput(FullName: String,CardNumber:String,Date:String)
    }

    var mOnInputSelected: OnInputSelected? = null
    lateinit var binding : FragmentShowBinding
    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): ShowFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = ShowFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View {
        binding= FragmentShowBinding.inflate(inflater,container,false)

        binding.btnSave.setOnClickListener {
            val cardName = binding.edtCardName.text.toString()
            val cardNumber = binding.edtCardNumber.text.toString()
            val expiryDate = binding.edtExpiryDate.text.toString()
            mOnInputSelected?.sendInput(cardName,cardNumber,expiryDate)
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputSelected = targetFragment as OnInputSelected?
        } catch (e: ClassCastException) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.message)
        }
    }

}