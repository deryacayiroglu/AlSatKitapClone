package com.cayiroglu.alsatkitapclone.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.cayiroglu.alsatkitapclone.R


object AlertDialogUtil {
    fun showDialog(context: Context,dialogType:DIALOG_TYPE){
        val alertDialog = AlertDialog.Builder(context)
        if(dialogType.equals(DIALOG_TYPE.NETWORK_DIALOG)){
            alertDialog.setTitle(context.resources.getString(R.string.alert_dialog_network_title))
            alertDialog.setMessage(context.resources.getString(R.string.alert_dialog_network_message))
            alertDialog.setPositiveButton(context.resources.getString(R.string.alert_dialog_positive_button), DialogInterface.OnClickListener { _, _ ->
                context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            })
            alertDialog.setNegativeButton(context.resources.getString(R.string.alert_dialog_negative_button), DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
                (context as Activity).finish()
            })
        }
        else if (dialogType.equals(DIALOG_TYPE.CLOSE_DIALOG)){
            alertDialog.setTitle(context.resources.getString(R.string.alert_dialog_close_title))
            alertDialog.setMessage(context.resources.getString(R.string.alert_dialog_close_message))
            alertDialog.setPositiveButton(context.resources.getString(R.string.alert_dialog_positive_button), DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
                (context as Activity).finish()
            })
            alertDialog.setNegativeButton(context.resources.getString(R.string.alert_dialog_negative_button), DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        }

        alertDialog.show()
    }
}
enum class DIALOG_TYPE{
    NETWORK_DIALOG,
    CLOSE_DIALOG
}