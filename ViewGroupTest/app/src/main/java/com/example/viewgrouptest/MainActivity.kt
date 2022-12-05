package com.example.viewgrouptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewgrouptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val adapter = CustomAdapter(this::onButtonClick)
    val listFirstName = listOf("Bạch","Nguyễn","Đặng","Lê","Hoàng","Phạm","Đào")
    val listLastName = listOf("Sang","Nam","Phú","Lan","Huy","Vũ","Nga")
    val listMidName = listOf("Văn","Xuân","Hồng","Hoàng","Thái")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupData()
    }

    private fun setupData(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
        adapter.setData(getUsers())
    }

    private fun getUsers(): List<Users> {
        val users = mutableListOf<Users>()
        for(item in listFirstName){
            users.add(Users(listFirstName.random(),listMidName.random().plus(" ").plus(listLastName.random()),getRandomString((6..10).random()),(10..50).random()))
        }
        return users
    }
    private fun onButtonClick(position:Int){
        adapter.notifyItemChanged(position)
    }
    fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}