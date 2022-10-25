package com.avidprogrammers.rupeecollector.view

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.avidprogrammers.rupeecollector.databinding.ActivityLoginBinding
import com.avidprogrammers.rupeecollector.model.BaseResponse
import com.avidprogrammers.rupeecollector.model.login.LoginResponse
import com.avidprogrammers.rupeecollector.util.SessionManager
import com.avidprogrammers.rupeecollector.viewmodel.login.LoginViewModel
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userIdValue = SessionManager.getUserId(this)

        if (userIdValue != null) {
            binding.userIdET.setText(userIdValue)
            binding.userIdET.isFocusable = false
            Log.d("loginScreen123", "userIdValue- $userIdValue")
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.loginBtn.setOnClickListener {

            if(binding.userIdET.text.isEmpty()) {
                binding.userIdET.hint = "Enter User ID"
                binding.userIdET.error = "Enter User ID"
            } else if(binding.userPinET.text.isEmpty()){
                binding.userPinET.hint = "Enter User Pin"
                binding.userPinET.error = "Enter User Pin"
            } else {
                doLogin()
            }
        }

    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    private fun doLogin() {
        val userId = binding?.userIdET?.text.toString().toInt()
        val userPin = binding?.userPinET?.text.toString().toInt()
        viewModel.loginUser(userId = userId, userPin = userPin)
    }

    private fun showLoading() {
        binding.progressBarContainer.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBarContainer.visibility = View.GONE
    }

    private fun processLogin(data: LoginResponse?) {
        if (data!!.message == "OK") {
            val userId = binding.userIdET.text.toString()
            val imei = fetchImei()

            data.let {
                SessionManager.saveUserId(this, userId)
                Log.d("loginScreen123", "saved userId- $userId")
                SessionManager.saveUserImei(this, imei)
                navigateToHome()
            }
        } else {
            processError(data.message)
        }
    }

    private fun fetchImei(): String {
        val uuid = UUID.randomUUID().toString()
        Log.d("loginScreen123", "UUID- $uuid")
        return uuid
    }

    private fun processError(msg: String?) {
        showToast("Error:$msg")
        Log.d("loginScreen123", "ErrorMessage- $msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}