package com.example.assignmentday12.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentday12.R
import com.example.assignmentday12.databinding.FragmentUserBinding
import com.example.assignmentday12.model.adapter.UserAdapter
import com.example.assignmentday12.view.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var userList: List<User>
    private val adapter = UserAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater)
        userList = addUser()
        setHasOptionsMenu(true)
        adapter.submitData(userList)
        binding.recyclerUser.adapter = adapter
        binding.recyclerUser.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    fun addUser(): List<User> {
        val tempList = mutableListOf<User>()
        for (i in 1 until 100) {
            tempList.add(User(i, getRandomName(), getRandomGender(), getRandomClass()))
        }
        return tempList
    }

    private fun filterList(text: String) {
        val filteredlist: ArrayList<User> = ArrayList()
        for (item in userList) {
            if (item.className.toLowerCase().contains(text.toLowerCase()) || item.name.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            adapter.filterList(filteredlist)
        } else {
            adapter.filterList(filteredlist)
            Log.d("test", filteredlist.toString())
        }
    }

    fun getRandomName(): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..Random.nextInt(5, 8))
            .map { charset.random() }
            .joinToString("")
    }

    fun getRandomClass(): String {
        val className = "A".plus(Random.nextInt(1, 10))
        return className
    }

    fun getRandomGender(): String {
        var gender = "Male"
        if (Random.nextBoolean()) {
            gender = "Female"
        }
        return gender
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu, menu)
        val search = menu.findItem(R.id.item_search)
        val searchView = search.actionView as SearchView
        Observable.create<String> { emitter ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!emitter.isDisposed) {
                        if (newText != null) {
                            emitter.onNext(newText)
                        }
                    }
                    return false
                }

            })
        }.debounce(800, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("Test", "Search:$it")
                    filterList(it.toString())
                },
                {
                    Log.e("Test", "Error:$it")
                },
                {
                    Log.d("Test", "Complete")
                }
            )

        return super.onCreateOptionsMenu(menu, inflater)
    }
}