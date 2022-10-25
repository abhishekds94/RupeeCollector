package com.avidprogrammers.rupeecollector.model.userDetails

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("message")
    var message: String
)