package com.rubin.jetpackcomponents.navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_navigation_main.*

class NavigationMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_main)
        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this@NavigationMainActivity, R.id.navHostFragment)

        setUpBottomNav(navController)
        setUpSideNav(navController)
        setUpActionBar(navController)
    }

    private fun setUpBottomNav(navController: NavController) {
        bottomNav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setUpSideNav(navController: NavController) {
        navView?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setUpActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.navHostFragment), drawerLayout)
    }
}