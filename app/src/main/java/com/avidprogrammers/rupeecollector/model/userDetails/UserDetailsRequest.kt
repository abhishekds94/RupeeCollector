package com.avidprogrammers.rupeecollector.model.userDetails

import com.google.gson.annotations.SerializedName

data class UserDetailsRequest(
    @SerializedName("userid")
    var userId: Int,

    @SerializedName("userpin")
    var userPin: Int,

    @SerializedName("address")
    var address: String,

    @SerializedName("imei")
    var imei: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("timezone")
    var timezone: String,

    @SerializedName("company")
    var company: String
)