package com.cayiroglu.alsatkitapclone.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.getImage(url: String){
    Glide.with(this.context)
            .load(url)
            .fitCenter()
            .into(this)

}