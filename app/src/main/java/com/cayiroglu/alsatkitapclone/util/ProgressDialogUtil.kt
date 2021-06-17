package com.cayiroglu.alsatkitapclone.util

import android.app.ProgressDialog
import android.content.Context
import com.cayiroglu.alsatkitapclone.R

object ProgressDialogUtil {
    fun showProgressDialog(context: Context):ProgressDialog{
        val progressDialog:ProgressDialog = ProgressDialog(context)
        progressDialog.setMessage(context.resources.getString(R.string.progress_dialog_text))
        progressDialog.show()
        return progressDialog
    }
}