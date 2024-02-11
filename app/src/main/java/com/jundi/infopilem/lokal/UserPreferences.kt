package com.jundi.infopilem.lokal

import android.content.Context
import com.jundi.infopilem.movieList.data.remote.respond.login.ResponseLogin

class  UserPreferences(context: Context) {
    companion object {
        private val PREF_NAME = "user_preferences"
        private val USERNAME = "username"
        private val PASSWORD = "password"
    }

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // fungsi untuk menyimpan data
    fun setUser(responseLogin: ResponseLogin) {
        val set = preferences.edit()
        set.putString(USERNAME, responseLogin.username)
        set.putString(PASSWORD, responseLogin.password)

        set.apply()
    }

    // fungsi untuk mengambil data
    fun getUser():ResponseLogin {
        val data = ResponseLogin()
        data.username = preferences.getString(USERNAME, null)
        data.password = preferences.getString(PASSWORD, null)

        return data
    }
}