package com.avidprogrammers.rupeecollector.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("userid")
    var userId: Int,

    @SerializedName("userpin")
    var userPin: Int
)