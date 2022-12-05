package com.example.jetpackday1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.jetpackday1.databinding.ActivityNavBinding

class NavScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHost.navController

        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment
            ),binding.drawer
        )
        setupActionBarWithNavController(navController,appBarConfig)
        binding.bnvMain.setupWithNavController(navController)
        binding.navMain.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //dat trung ten trong menu la co the k can goi ham duoi
        if (item.itemId == R.id.terms) {
            navController.navigate(R.id.action_global_termsFragment)
            return true
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}