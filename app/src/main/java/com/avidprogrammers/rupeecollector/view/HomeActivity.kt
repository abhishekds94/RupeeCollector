package com.avidprogrammers.rupeecollector.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
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

        viewModel.userDetailsResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processUserDetails(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.userDetailsBtn.setOnClickListener {
            uploadUserDetails()
        }

    }

    private fun uploadUserDetails() {
        val userIdValue = SessionManager.getUserId(applicationContext)
        val userId = userIdValue?.toInt()
        val userPin = 1234
        val address = binding?.address?.text.toString()
        val imei = binding?.imei?.text.toString()
        val description = binding?.description?.text.toString()
        val timezone = binding?.timezone?.text.toString()
        val company = binding?.company?.text.toString()
        viewModel.userDetails(
            userId = userId!!,
            userPin = userPin,
            address = address,
            imei = imei,
            description = description,
            timezone = timezone,
            company = company
        )
    }

    private fun showLoading() {
        binding.progressBarContainer.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBarContainer.visibility = View.GONE
    }

    private fun processUserDetails(data: UserDetailsResponse?) {
        if (data!!.message == "OK") {
            showToast("Success!!: ${data.message}")
            stopLoading()
        } else {
            processError(data.message)
        }
    }

    private fun processError(msg: String?) {
        showToast("Error:$msg")
        Log.d("userDetailScreen123", "ErrorMessage- $msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}