package com.avidprogrammers.rupeecollector.viewmodel.userDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avidprogrammers.rupeecollector.model.BaseResponse
import com.avidprogrammers.rupeecollector.model.login.LoginRepository
import com.avidprogrammers.rupeecollector.model.login.LoginRequest
import com.avidprogrammers.rupeecollector.model.login.LoginResponse
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsRepository
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsRequest
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsResponse
import kotlinx.coroutines.launch

class UserDetailsViewModel(application: Application) : AndroidViewModel(application) {

    val userDetailsRepo = UserDetailsRepository()
    val userDetailsResult: MutableLiveData<BaseResponse<UserDetailsResponse>> = MutableLiveData()

    fun userDetails(userId: Int, userPin: Int, address: String, imei:String, description:String, timezone:String, company:String) {
        userDetailsResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val userDetailsRequest = UserDetailsRequest(
                    userId = userId,
                    userPin = userPin,
                    address = address,
                    imei = imei,
                    description = description,
                    timezone = timezone,
                    company = company
                )
                val response = userDetailsRepo.userDetails(userDetailsRequest = userDetailsRequest)

                if (response?.code() == 201) {
                    userDetailsResult.value = BaseResponse.Success(response.body())
                } else {
                    BaseResponse.Error(response?.message())
                }
            } catch (ex: Exception) {
                userDetailsResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}