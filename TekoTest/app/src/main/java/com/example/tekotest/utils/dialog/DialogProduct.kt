package com.example.tekotest.utils.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.tekotest.data.models.Color
import com.example.tekotest.data.models.Product
import com.example.tekotest.databinding.AddProductDialogBinding
import com.example.tekotest.utils.ValidateForm
import com.example.tekotest.viewmodel.ProductViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout


class DialogProduct(
    private val listener: (product: Product) -> Unit
) : DialogFragment() {
    private lateinit var binding: AddProductDialogBinding
    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var colorDropDown: MaterialAutoCompleteTextView
    private lateinit var arrayAdapter: ArrayAdapter<Color>
    private val list = arrayListOf<Color>()

    private var product: Product? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddProductDialogBinding.inflate(layoutInflater)
        arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            list
        )
        viewModel.colorList.observe(this) {
            list.clear()
            list.addAll(it)
            arrayAdapter.notifyDataSetChanged()
        }
        product?.let { setData(it) }
        val validateForm = ValidateForm(requireContext())
        binding.apply {
            colorDropDown = edtColor.editText as MaterialAutoCompleteTextView
            colorDropDown.setAdapter(arrayAdapter)

            btnUpdate.apply {
                setOnClickListener {
                    validateForm.apply {
                        validateProductName(edtNameProduct)
                        validateSKU(edtSku)
                        if (isValidate) {
                            listener(
                                Product(
                                    id = product?.id!!,
                                    errorDescription = product?.errorDescription!!,
                                    name = edtNameProduct.editText?.text.toString(),
                                    sku = edtSku.editText?.text.toString().toLong(),
                                    image = product?.image!!,
                                    color = edtColor.editText?.text.toString()
                                )
                            )
                            dismiss()
                        }
                    }
                }
            }
            btnCancel.setOnClickListener { dismiss() }
        }
        handleClearValidate()
        return MaterialAlertDialogBuilder(requireContext()).setView(binding.root).create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialog.cancel()
    }


    private fun setData(product: Product) {
        binding.apply {
            edtNameProduct.editText?.setText(product.name)
            edtSku.editText?.setText(product.sku.toString())
            edtColor.editText?.setText(product.color)
        }
    }

    fun showDialog(childFragment: FragmentManager, product: Product? = null) {
        show(childFragment, "Update")
        this.product = product
    }

    private fun handleClearValidate() {
        binding.apply {
            edtNameProduct.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate(edtNameProduct)
                }
            }
            edtSku.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate(edtSku)
                }
            }
        }
    }

    private fun clearValidate(txt: TextInputLayout) {
        txt.apply {
            error = null
            helperText = null
        }
    }
}