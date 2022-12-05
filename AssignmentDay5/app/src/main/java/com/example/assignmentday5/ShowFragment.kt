package com.example.assignmentday5

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentday5.adapter.CustomAdapter
import com.example.assignmentday5.adapter.InfoAdapter
import com.example.assignmentday5.data.Info
import com.example.assignmentday5.data.user
import com.example.assignmentday5.databinding.FragmentShowBinding
import com.google.android.material.navigation.NavigationView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class ShowFragment : Fragment() {
    lateinit var binding: FragmentShowBinding
    private var users = mutableListOf<user>()
    private var info = mutableListOf<Info>()
    private val parentAdapter = CustomAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowBinding.inflate(layoutInflater)
        setupData()
        setHasOptionsMenu(true)
        return binding.root
    }

        private fun filter(text: String) {
            val filteredlist: ArrayList<user> = ArrayList()
            for (item in users) {
                if (item.Name.toLowerCase().contains(text.toLowerCase())) {
                    filteredlist.add(item)
                }
            }
            if (filteredlist.isEmpty()) {
                parentAdapter.filterList(filteredlist)
                Toast.makeText(context, "No result", Toast.LENGTH_SHORT).show()
            } else {
                parentAdapter.filterList(filteredlist)
            }
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu, menu)
            val search = menu.findItem(R.id.itemSearch)
            val searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                Log.d("test", filter(newText.toString()).toString())
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)

    }

    private fun setupData() {
        binding.listItem.layoutManager = LinearLayoutManager(context)
        binding.listItem.adapter = parentAdapter
        parentAdapter.setData(getDataUser())
    }

    fun getDataUser(): List<user> {
        users.clear()
        val listUser = listOf("Mary", "Conan", "Linda")
        val listAge = listOf(21, 25, 24)
        val listSex = listOf(0, 1, 0)
        for (i in 0 until listUser.size) {
            val name = listUser[i]
            val age = listAge[i]
            val sex = listSex[i]
            users.add(user(name, age, sex))
        }

        return users
    }

    fun getDataInfo(): List<Info> {
        val listExercise = listOf(
            "KT-101 Kotlin, Exercise3",
            "KT-101 Kotlin, Exercise2",
            "KT-101 Kotlin, Exercise1",
            "KT-101 Kotlin, Exercise6",
            "KT-101 Kotlin, Exercise4",
            "KT-101 Kotlin, Exercise5",
            "KT-101 Kotlin, Exercise7",
            "KT-101 Kotlin, Exercise8",
            "KT-101 Kotlin, Exercise9"
        )
        val listSubject = listOf(
            "Services",
            "Permission",
            "ViewGroup",
            "Activity",
            "Fragment",
            "Animation",
            "Drawable",
            "CustomView",
            "Android Component"
        )
        val listDate = listOf(
            "28/07/2022",
            "29/07/2022",
            "30/07/2022",
            "31/07/2022",
            "01/08/2022",
            "02/08/2022",
            "03/08/2022",
            "04/08/2022",
            "05/08/2022"
        )
        val listTime = listOf("180", "270", "300", "120", "160", "200", "60", "90", "100")
        val listDayLeft = listOf("5", "4", "3", "1", "2", "6", "7", "8", "9")
        val random = Random.nextInt(0, 8)
        for (i: Int in 0..random) {
            val randomIndex = Random.nextInt(listSubject.size);
            val exercise = listExercise[randomIndex]
            val subject = listSubject[randomIndex]
            val date = listDate[randomIndex]
            val time = listTime[randomIndex]
            val dayLeft = listDayLeft[randomIndex]
            info.add(Info(exercise, subject, date, time, dayLeft))
        }
        return info
    }
}