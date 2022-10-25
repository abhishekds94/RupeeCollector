package com.avidprogrammers.rupeecollector.model.login

import com.avidprogrammers.rupeecollector.model.retrofit.LoginApi
import retrofit2.Response

class LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return LoginApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}