package br.com.igorbag.githubsearch.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveUserName(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString("username", "") ?: ""
    }
}