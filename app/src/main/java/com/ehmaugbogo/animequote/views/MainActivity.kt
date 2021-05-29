package com.ehmaugbogo.animequote.views

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.ehmaugbogo.animequote.R
import com.ehmaugbogo.animequote.api.achitecture.AnimeViewModel
import com.ehmaugbogo.animequote.util.Result

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        initProgram()

        val progress = findViewById<ProgressBar>(R.id.progress)


        viewModel.getAllAvailableAnimes().observe(this){ result ->
            when(result){
                Result.Loading -> progress.isVisible = true
                is Result.Failure -> {
                    progress.isVisible = false
                    showSnackBar(progress, result.error.localizedMessage)
                }
                is Result.Success -> {
                    progress.isVisible = false
                    showSnackBar(progress, result.data?.first()!!)
                    Log.d(TAG, "${result.data}")
                }
            }

        }
    }

    private fun initProgram() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            showSnackBar(view, "Replace with your own action")
        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}


fun showSnackBar(view: View, msg: String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
}