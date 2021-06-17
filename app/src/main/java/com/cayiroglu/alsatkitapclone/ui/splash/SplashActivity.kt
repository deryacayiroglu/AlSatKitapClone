package com.cayiroglu.alsatkitapclone.ui.splash

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.cayiroglu.alsatkitapclone.R
import com.cayiroglu.alsatkitapclone.ui.login.LoginActivity
import com.cayiroglu.alsatkitapclone.util.AlertDialogUtil
import com.cayiroglu.alsatkitapclone.util.Constants
import com.cayiroglu.alsatkitapclone.util.DIALOG_TYPE
import com.cayiroglu.alsatkitapclone.util.NetworkUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startTimer()
    }

    fun startTimer(){
        object :CountDownTimer(Constants.TIMER_MILLIS, Constants.TIMER_INTERVAL){
            override fun onFinish() {
               checkedInternet()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }

    fun checkedInternet(){
        if(NetworkUtil.isConnectedToInternet(this)){
            startLoginActivity()
        }
        else{
            AlertDialogUtil.showDialog(this,DIALOG_TYPE.NETWORK_DIALOG)
        }
    }

    fun startLoginActivity(){
        val loginIntent= Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

}