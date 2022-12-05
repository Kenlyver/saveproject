package com.example.serverorder.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serverorder.R
import com.example.serverorder.adapter.OrderAdapter
import com.example.serverorder.adapter.SwipeToDeleteCallBack
import com.example.serverorder.databinding.FragmentShowBinding
import com.example.serverorder.model.ConnectTable
import com.example.serverorder.model.Order
import com.example.serverorder.viewModel.ShowViewModel

class ShowFragment : Fragment() {
    private lateinit var binding: FragmentShowBinding
    private val viewModel: ShowViewModel by viewModels()
    private lateinit var listOrder: List<ConnectTable>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(layoutInflater)
        val adapter = OrderAdapter()
        viewModel.getOrder.observe(viewLifecycleOwner) {
            listOrder = it
            adapter.submitData(it)
        }
        setHasOptionsMenu(true)
        binding.rvOrder.adapter = adapter
        binding.rvOrder.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val action =
                    ShowFragmentDirections.actionShowFragmentToUpdateFragment(listOrder[position])
                findNavController().navigate(action)
            }
        })
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Order>("Insert")
            ?.observe(viewLifecycleOwner) { result ->
                viewModel.insertOrder(result)
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Order>("Update")
            ?.observe(viewLifecycleOwner) { result ->
                viewModel.updateOrder(result)
            }
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
                viewModel.apply {
                    val order = getOrder.value?.let {
                        it[position]
                    }
                    order.let { deleteOrder(it?.order!!) }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandle)
        itemTouchHelper.attachToRecyclerView(binding.rvOrder)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}