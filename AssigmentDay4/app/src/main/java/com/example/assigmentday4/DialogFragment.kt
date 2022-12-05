package com.example.assigmentday4

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assigmentday4.databinding.CustomDialogBinding
import com.example.assigmentday4.databinding.FragmentDialogBinding
import java.util.*

class DialogFragment : Fragment() {
    companion object {
        fun newInstance(): DialogFragment {
            return DialogFragment()
        }
    }

    lateinit var binding: FragmentDialogBinding
    lateinit var bindingDialog: CustomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBinding.inflate(layoutInflater)
        binding.txtName.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val inflater = layoutInflater
            builder.apply {
                setTitle("Name")
                bindingDialog = CustomDialogBinding.inflate(layoutInflater, binding.root, false)
                setView(bindingDialog.root)
                setPositiveButton("OK") { dialogInterface, i ->
                    binding.txtName.text = bindingDialog.editUserName.text.toString()
                }
                show()
            }
        }
        binding.txtBirthday.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    month += 1
                    binding.txtBirthday.setText("$dayOfMonth/$month/$year")
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        binding.txtSex.setOnClickListener {
            val listItems = arrayOf("Male", "Female", "Other")
            var save: Int = 0
            val builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle("Choose an item")
                setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                    save = i
                }
                setPositiveButton("OK") { dialogInterface, i ->
                    binding.txtSex.text = listItems[save]
                }
                setNeutralButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }
            }
            val mDialog = builder.create()
            mDialog.show()
        }
        binding.txtFavorite.setOnClickListener {
            val items = arrayOf("DevC", "Java", "Kotlin", "PHP")
            val selectedList = ArrayList<Int>()
            val builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle("Favorite Subject")
                setMultiChoiceItems(
                    items, null
                ) { dialog, which, isChecked ->
                    if (isChecked) {
                        selectedList.add(which)
                    } else if (selectedList.contains(which)) {
                        selectedList.remove(Integer.valueOf(which))
                    }
                }
                setPositiveButton("DONE") { dialogInterface, i ->
                    val selectedStrings = ArrayList<String>()

                    for (j in selectedList.indices) {
                        selectedStrings.add(items[selectedList[j]])
                    }
                    binding.txtFavorite.setText(Arrays.toString(selectedStrings.toTypedArray()))
                }
                show()
            }
        }
        return binding.root
    }

}