package com.avidprogrammers.rupeecollector.model.userDetails

import com.avidprogrammers.rupeecollector.model.retrofit.LoginApi
import com.avidprogrammers.rupeecollector.model.retrofit.UserDetailsApi
import retrofit2.Response

class UserDetailsRepository {
    suspend fun userDetails(userDetailsRequest: UserDetailsRequest): Response<UserDetailsResponse>? {
        return UserDetailsApi.getApi()?.userDetails(userDetailsRequest = userDetailsRequest)
    }
}