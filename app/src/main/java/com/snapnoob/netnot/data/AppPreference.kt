package com.snapnoob.netnot.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

interface AppPreference {
    fun setApiKey(apiKey: String)
    fun getApiKey(): String
}

class AppPreferenceImpl @Inject constructor(
    private val context: Context
) : AppPreference {

    private fun getSharedPreference(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun setApiKey(apiKey: String) {
        getSharedPreference().edit().putString(API_KEY, apiKey).apply()
    }

    override fun getApiKey(): String =
        getSharedPreference().getString(API_KEY, "")!!

    companion object {
        private const val SHARED_PREF_NAME = "netnot_pref"

        private const val API_KEY = "api_key"
    }
}