package com.avidprogrammers.rupeecollector.model.retrofit

import com.avidprogrammers.rupeecollector.model.login.LoginRequest
import com.avidprogrammers.rupeecollector.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("users/verify")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): LoginApi? {
            return ApiClient.client?.create(LoginApi::class.java)
        }
    }

}