package com.example.clientstudentapp.view.fragment

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clientstudent.view.adpter.StudentAdapter
import com.example.clientstudentapp.Constant
import com.example.clientstudentapp.Constant.ACTION_RECEIVER
import com.example.clientstudentapp.R
import com.example.clientstudentapp.databinding.FragmentStudentBinding
import com.example.serverstudentapp.ServerAIDL
import com.example.serverstudentapp.model.Student


class StudentFragment : Fragment() {

    private lateinit var binding: FragmentStudentBinding
    private lateinit var studentService: ServerAIDL
    private lateinit var student: List<Student>
    private val studentAdapter = StudentAdapter()
    private var checkConnect = false
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.run {
                if (action == ACTION_RECEIVER) {
                    val list = getParcelableArrayListExtra<Parcelable>("DATA")
                    student = list as List<Student>
                    studentAdapter.submitData(list as List<Student>)
                }
            }
        }

    }
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            studentService = ServerAIDL.Stub.asInterface(service)
            checkConnect = true
            studentService.sendAction(Constant.ACTION_RECEIVER)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            checkConnect = false
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)

        val search = menu.findItem(R.id.itemSearch)
        val searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val student = studentService.findStudent(newText.toString().lowercase())
                studentAdapter.submitData(student)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortAscending -> studentAdapter.submitData(studentService.sortStudent(1))
            R.id.sort -> studentAdapter.submitData(studentService.sortStudent(2))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        binding.recyclerStudent.adapter = studentAdapter
        binding.recyclerStudent.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Student>("Insert")
            ?.observe(viewLifecycleOwner) { result ->
                studentService.insertStudent(result)
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Student>("Update")
            ?.observe(viewLifecycleOwner) { result ->
                studentService.updateStudent(result)
            }
        studentAdapter.setOnItemClickListener(object : StudentAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val action =
                    StudentFragmentDirections.actionStudentFragmentToUpdateFragment(student[position])
                findNavController().navigate(action)
            }

        })
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        requireActivity().registerReceiver(receiver, IntentFilter(ACTION_RECEIVER))
        Intent("com.example.serverstudentapp.RemoteService").apply {
            setPackage("com.example.serverstudentapp")
            requireActivity().bindService(this, connection, Activity.BIND_AUTO_CREATE)
        }
    }
}