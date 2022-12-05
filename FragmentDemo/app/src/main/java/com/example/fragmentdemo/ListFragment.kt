package com.example.fragmentdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentdemo.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding:FragmentListBinding
    private val adapter = CustomAdapter(this::OnItemClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding=FragmentListBinding.inflate(inflater,container,false)
        setupData()
        return binding.root
    }
    private fun setupData() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        adapter.setData(getUsers())
    }

    private fun getUsers(): List<Users> {
        val users = mutableListOf<Users>()
        users.add(Users("Trần Văn A", genNumberAccount(), "09/22"))
        users.add(Users("Trần Dũng", genNumberAccount(), "01/26"))
        users.add(Users("Nguyễn Văn", genNumberAccount(), "03/25"))
        users.add(Users("Lê Nho", genNumberAccount(), "09/25"))
        return users
    }

    fun genNumberAccount(): String {
        var numTemp: String = ""
        for (i in 0..3) {
            var number: String = (1000..9999).random().toString()
            numTemp += number
        }
        val result = buildString {
            for (i in 0 until numTemp.length) {
                if (i % 4 == 0 && i > 0)
                    append(' ')
                append(numTemp[i])
            }
        }
        Log.d("check", result)
        return result
    }
    private fun OnItemClick(user: Users, position: Int) {
        adapter.notifyItemChanged(position)
    }

}