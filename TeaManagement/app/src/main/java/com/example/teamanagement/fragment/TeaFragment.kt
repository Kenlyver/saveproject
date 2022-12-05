package com.example.teamanagement.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamanagement.R
import com.example.teamanagement.adapter.SwipeToDeleteCallBack
import com.example.teamanagement.adapter.TeaAdapter
import com.example.teamanagement.databinding.FragmentTeaBinding
import com.example.teamanagement.model.Tea
import com.example.teamanagement.view.MainActivity
import com.example.teamanagement.viewModel.TeaViewModel
import com.example.teamanagement.viewModel.WorkerViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TeaFragment : Fragment() {
    private lateinit var binding: FragmentTeaBinding
    private val viewModel: TeaViewModel by viewModels()
    private val viewM: WorkerViewModel by viewModels()
    val activityFab: FloatingActionButton?
        get() = (activity as? MainActivity)?.binding?.fabAdd
    private lateinit var listTea: List<Tea>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeaBinding.inflate(inflater, container, false)
        activityFab?.show()
        setHasOptionsMenu(true)
        val adapter = TeaAdapter()
        viewModel.tea.observe(viewLifecycleOwner, Observer {
            listTea = it
            adapter.submitData(it)
        })
        binding.rvTea.adapter = adapter
        binding.rvTea.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.setOnItemClickListener(object : TeaAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val action =
                    TeaFragmentDirections.actionTeaFragmentToAddTeaFragment(listTea[position])
                findNavController().navigate(action)
            }
        })
        val swipeHandle = object : SwipeToDeleteCallBack(requireContext()) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val id = listTea[position].id
                if (id != null) {
                    viewModel.deleteTeaId(id)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandle)
        itemTouchHelper.attachToRecyclerView(binding.rvTea)
        activityFab?.setOnClickListener {
            val action = TeaFragmentDirections.actionTeaFragmentToAddTeaFragment(null)
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tea_notification, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Notification) {
            viewM.enqueuePeriodic()
        }
        return super.onOptionsItemSelected(item)
    }
}