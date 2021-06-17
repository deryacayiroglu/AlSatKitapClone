package com.cayiroglu.alsatkitapclone.data.repository

import com.cayiroglu.alsatkitapclone.data.CategoryDataSource
import com.cayiroglu.alsatkitapclone.data.model.CategoryModel
import com.cayiroglu.alsatkitapclone.data.remote.RemoteCategoryDataSource
import com.cayiroglu.alsatkitapclone.util.Resource
import kotlinx.coroutines.flow.Flow

class CategoryRepository {

    private var caategoryDataSource: CategoryDataSource?=null

    init {
        caategoryDataSource= RemoteCategoryDataSource()
    }

    fun getCategories(): Flow<Resource<List<CategoryModel>>>
    {
        return caategoryDataSource!!.getCategories()
    }

}