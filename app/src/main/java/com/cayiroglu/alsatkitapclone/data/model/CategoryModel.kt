package com.cayiroglu.alsatkitapclone.data.model

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@Keep
@IgnoreExtraProperties
class CategoryModel {

    @PropertyName("categoryName")
    val categoryName: String?=null

    @PropertyName("imageUrl")
    val imageUrl: String?=null

}