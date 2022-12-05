package com.example.allapplication

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.allapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var app = ArrayList<App>()
    lateinit var listApp: List<App>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInstalledApps()
        val listAdapter = GridAdapter(listApp = app, this@MainActivity)
        binding.gridView.adapter = listAdapter



//        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
//
//            Toast.makeText(
//                applicationContext, listApp[position].appName + " selected",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }
    private fun getApps(): List<App> {
        val packs = packageManager.getInstalledPackages(0)
        for (i in packs!!.indices) {
            val p = packs.get(i)
            if (!p?.let { isSystemPackage(it) }!!) {
                val appName = p.applicationInfo?.loadLabel(packageManager).toString()
                val icon = p.applicationInfo?.loadIcon(packageManager)
                val packages = p.applicationInfo.packageName
                listApp = listApp + App(appName, icon!!, packages)
            }
        }
        Log.d("appList", listApp.toString())
        return listApp
    }
    private fun getInstalledApps(): ArrayList<App> {
        app.clear()
        val packs = packageManager.getInstalledPackages(0)
        for (i in packs.indices) {
            val p = packs[i]
            if (!isSystemPackage(p)) {
                val appName = p.applicationInfo.loadLabel(packageManager).toString()
                val icon = p.applicationInfo.loadIcon(packageManager)
                val packages = p.applicationInfo.packageName
                app.add(App(appName, icon, packages))
            }
        }
        Log.d("appList", app.toString())
        return app
    }
    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }
}