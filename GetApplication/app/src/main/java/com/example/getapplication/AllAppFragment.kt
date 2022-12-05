package com.example.getapplication

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.getapplication.databinding.FragmentAllAppBinding
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AllAppFragment : Fragment() {
    lateinit var binding: FragmentAllAppBinding
    private var app = mutableListOf<App>()
    private val adapter = CustomAdapter(this::OnItemClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllAppBinding.inflate(inflater, container, false)
        setupData()
        setHasOptionsMenu(true)
        binding.scrollRight.setOnClickListener {
            binding.listItem.smoothScrollBy(400, 0)
        }
        binding.scrollLeft.setOnClickListener {
            binding.listItem.smoothScrollBy(-400, 0)
        }
        return binding.root
    }

    private fun setupData() {
        binding.listItem.layoutManager =
            GridLayoutManager(context, 4, GridLayoutManager.HORIZONTAL, false)
        binding.listItem.adapter = adapter
        adapter.setData(getApps())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortName -> {
                app.sortBy { it.appName.capitalized() }
                adapter.setData(app)
            }
            R.id.sortInstallTime->{
                val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                app.sortByDescending { LocalDate.parse(it.installTime,dateTimeFormatter) }
                adapter.setData(app)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getApps(): List<App> {
        app.clear()
        val packages= activity?.packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
        packages?.forEach { packageInfo:ApplicationInfo->
            val appName = packageInfo.loadLabel(activity!!.packageManager).toString()
            val icon = packageInfo.loadIcon(activity!!.packageManager)
            val packages = packageInfo.packageName
            val appFile = packageInfo.sourceDir
            val dateFormat = SimpleDateFormat("dd-MM-yyyy")
            val installed = File(appFile).lastModified()
            val time = dateFormat.format(installed)

            app.add(App(appName, icon!!, packages, time.toString()))
        }
        Log.d("appList", app.toString())
        return app
    }

    private fun String.capitalized(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }

    private fun OnItemClick(app: App, position: Int) {
        adapter.notifyItemChanged(position)
    }
}