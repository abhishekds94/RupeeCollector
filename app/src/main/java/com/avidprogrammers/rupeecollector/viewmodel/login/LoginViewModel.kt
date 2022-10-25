package com.avidprogrammers.rupeecollector.viewmodel.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avidprogrammers.rupeecollector.model.BaseResponse
import com.avidprogrammers.rupeecollector.model.login.LoginRepository
import com.avidprogrammers.rupeecollector.model.login.LoginRequest
import com.avidprogrammers.rupeecollector.model.login.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginRepo = LoginRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(userId: Int, userPin: Int) {
        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    userId = userId,
                    userPin = userPin
                )
                val response = loginRepo.loginUser(loginRequest = loginRequest)

                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    BaseResponse.Error(response?.message())
                }
            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}