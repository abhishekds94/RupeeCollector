package com.avidprogrammers.rupeecollector.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("message")
    var message: String
)