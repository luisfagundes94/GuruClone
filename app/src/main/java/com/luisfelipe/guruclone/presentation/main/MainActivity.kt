package com.luisfelipe.guruclone.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luisfelipe.guruclone.R
import com.luisfelipe.guruclone.databinding.ActivityMainBinding
import com.luisfelipe.guruclone.utils.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) initBottomNavigation()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val bottomNavigationView: BottomNavigationView = binding.bottomNav
        val navGraphIds = getGraphIds()
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_container,
            intent = intent
        )
        controller.observe(this, { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    private fun getGraphIds(): List<Int> {
        return listOf(
            R.navigation.my_list,
            R.navigation.explore,
            R.navigation.wallet,
            R.navigation.profile
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}