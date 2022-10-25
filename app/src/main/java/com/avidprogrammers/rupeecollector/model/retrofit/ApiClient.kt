package com.avidprogrammers.rupeecollector.model.retrofit

import com.avidprogrammers.rupeecollector.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    var mOkHtpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    var mRetrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit
                    .Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(mOkHtpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit
        }
}