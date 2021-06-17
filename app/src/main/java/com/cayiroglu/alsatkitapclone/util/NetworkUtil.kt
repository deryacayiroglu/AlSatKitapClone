package com.cayiroglu.alsatkitapclone.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtil{
    fun isConnectedToInternet(context:Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        for (i in networkInfo.indices){
            if (networkInfo[i].state == NetworkInfo.State.CONNECTED){
                return true
            }
        }
        return false
    }
}