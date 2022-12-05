package com.example.assignmentday12.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentday12.R
import com.example.assignmentday12.databinding.FragmentUserBinding
import com.example.assignmentday12.model.adapter.UserAdapter
import com.example.assignmentday12.util.getQueryTextChangeStateFlow
import com.example.assignmentday12.view.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class UserFlowFragment : Fragment(), CoroutineScope {
    private lateinit var binding: FragmentUserBinding
    private lateinit var userList: List<User>
    private val adapter = UserAdapter()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater)
        userList = addUser()
        setHasOptionsMenu(true)
        adapter.submitData(userList)
        job = Job()
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

    private fun filterList(text: String): Flow<String> {
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
        }
        return flow {
            delay(2000)
            emit(text)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu, menu)
        val search = menu.findItem(R.id.item_search)
        val searchView = search.actionView as SearchView
        launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(800)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    filterList(query)
                        .catch {
                            emitAll(flowOf(""))
                        }
                }
                .flowOn(Dispatchers.Main)
                .collect { result ->
                    Log.d("test", result)
                }
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

}