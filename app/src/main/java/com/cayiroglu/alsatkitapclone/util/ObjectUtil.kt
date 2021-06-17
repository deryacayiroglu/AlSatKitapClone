package com.cayiroglu.alsatkitapclone.util

import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.google.gson.Gson

object ObjectUtil {

    fun <T> objectToString(objectData: T): String {
            val gson = Gson()
            return gson.toJson(objectData)
    }

    inline fun <reified T> jsonStringToObject(jsonString: String): T {
            val gson = Gson()
            return gson.fromJson(jsonString, T::class.java)
    }

}