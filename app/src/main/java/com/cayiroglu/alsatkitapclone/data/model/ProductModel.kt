package com.cayiroglu.alsatkitapclone.data.model

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@Keep
@IgnoreExtraProperties
class ProductModel {

    @PropertyName("bookName")
    val bookName: String?=null

    @PropertyName("imageUrl")
    val imageUrl: String?=null

    @PropertyName("categoryName")
    val categoryName: String?=null

    @PropertyName("bookState")
    val bookState: String?=null

    @PropertyName("publisher")
    val publisher: String?=null

    @PropertyName("author")
    val author: String?=null

    @PropertyName("seller")
    val seller: String?=null

    @PropertyName("price")
    val price: String?=null

    @PropertyName("rating")
    val rating: String?=null

    @PropertyName("explanation")
    val explanation: String?=null

    @PropertyName("numberOfPages")
    val numberOfPages: String?=null

}