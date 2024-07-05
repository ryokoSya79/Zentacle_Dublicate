package com.example.application_flamingo

import android.content.Context
import android.net.ConnectivityManager


class NetworkManager {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else {
            false
        }
    }
}