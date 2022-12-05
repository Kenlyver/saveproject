package com.example.teamanagement.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teamanagement.R
import com.example.teamanagement.databinding.FragmentAddTeaBinding
import com.example.teamanagement.model.Tea
import com.example.teamanagement.view.MainActivity
import com.example.teamanagement.viewModel.TeaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddTeaFragment : Fragment() {
    private lateinit var binding: FragmentAddTeaBinding
    private val viewModelAddTea: TeaViewModel by viewModels()
    private val args: AddTeaFragmentArgs by navArgs()
    private var update = false
    val activityFab: FloatingActionButton?
        get() = (activity as? MainActivity)?.binding?.fabAdd

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTeaBinding.inflate(inflater, container, false)
        activityFab?.hide()
        setHasOptionsMenu(true)
        if (!(args.tea == null)) {
            update = true
            binding.edtTeaName.setText(args.tea!!.name)
            binding.edtDescription.setText(args.tea!!.description)
            binding.edtTeaOrigin.setText(args.tea!!.origin)
            binding.edtIngredients.setText(args.tea!!.ingredients)
            binding.edtSteepTime.setText(args.tea!!.steepTime)
            binding.edtCaffeineLevel.setText(args.tea!!.caffeineLevel)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tea_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Save) {
            val name = binding.edtTeaName.text.toString()
            val description = binding.edtDescription.text.toString()
            val origin = binding.edtTeaOrigin.text.toString()
            val ingredients = binding.edtIngredients.text.toString()
            val steepTime = binding.edtSteepTime.text.toString()
            val caffeineLevel = binding.edtCaffeineLevel.text.toString()
            if (name.isEmpty() || description.isEmpty() || origin.isEmpty() || ingredients.isEmpty() || steepTime.isEmpty() || caffeineLevel.isEmpty()) {
                Toast.makeText(context, "Some field is empty", Toast.LENGTH_SHORT).show()
            } else {
                val tea = Tea(
                    null,
                    name = name,
                    description = description,
                    origin = origin,
                    ingredients = ingredients,
                    steepTime = steepTime,
                    caffeineLevel = caffeineLevel
                )
                if (update) {
                    val teaa = Tea(
                        id = args.tea?.id,
                        name = name,
                        description = description,
                        origin = origin,
                        ingredients = ingredients,
                        steepTime = steepTime,
                        caffeineLevel = caffeineLevel
                    )
                    viewModelAddTea.updateTea(teaa)
                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                    val action = AddTeaFragmentDirections.actionAddTeaFragmentToTeaFragment()
                    findNavController().navigate(action)
                } else {
                    viewModelAddTea.insertTea(tea)
                    Toast.makeText(context, "Add successful", Toast.LENGTH_SHORT).show()
                    val action = AddTeaFragmentDirections.actionAddTeaFragmentToTeaFragment()
                    findNavController().navigate(action)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}