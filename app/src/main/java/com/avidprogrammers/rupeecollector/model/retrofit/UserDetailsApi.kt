package com.avidprogrammers.rupeecollector.model.retrofit

import com.avidprogrammers.rupeecollector.model.login.LoginRequest
import com.avidprogrammers.rupeecollector.model.login.LoginResponse
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsRequest
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserDetailsApi {

    @POST("users")
    suspend fun userDetails(@Body userDetailsRequest: UserDetailsRequest): Response<UserDetailsResponse>

    companion object {
        fun getApi(): UserDetailsApi? {
            return ApiClient.client?.create(UserDetailsApi::class.java)
        }
    }

}