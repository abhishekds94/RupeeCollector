package com.avidprogrammers.rupeecollector.util

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import com.avidprogrammers.rupeecollector.R
import java.lang.reflect.Array.getInt
import kotlin.coroutines.coroutineContext

object SessionManager {

    const val USER_ID = "user_id"
    const val USER_IMEI = "abcd"

    fun saveUserId(context: Context, id: String) {
        saveId(context, USER_ID, id)
    }

    fun saveUserImei(context: Context, imei: String) {
        saveImei(context, USER_IMEI, imei)
    }

    fun getUserId(context: Context): String? {
        return getId(context, USER_ID)
    }

    fun getUserImei(context: Context): String? {
        return getImei(context, USER_IMEI)
    }

    fun saveId(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveImei(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getId(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(this.USER_ID, null)
    }

    private fun getImei(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(this.USER_IMEI, null)
    }

    private fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

}