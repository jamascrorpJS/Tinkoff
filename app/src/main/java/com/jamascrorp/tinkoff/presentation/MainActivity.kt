package com.jamascrorp.tinkoff.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewBinding: ActivityMainBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Tinkoff)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.mainScreen,
            R.id.payScreen
        ))
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottom.setupWithNavController(navController)
    }
}