package com.avidprogrammers.rupeecollector.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.avidprogrammers.rupeecollector.R
import com.avidprogrammers.rupeecollector.databinding.ActivityHomeBinding
import com.avidprogrammers.rupeecollector.databinding.ActivityLoginBinding
import com.avidprogrammers.rupeecollector.model.BaseResponse
import com.avidprogrammers.rupeecollector.model.login.LoginResponse
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsResponse
import com.avidprogrammers.rupeecollector.util.SessionManager
import com.avidprogrammers.rupeecollector.viewmodel.login.LoginViewModel
import com.avidprogrammers.rupeecollector.viewmodel.userDetails.UserDetailsViewModel
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<UserDetailsViewModel>()

//    val userImeiValue = SessionManager.getUserImei(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //setup bottom nav bar
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.itemIconTintList = null

    }

}