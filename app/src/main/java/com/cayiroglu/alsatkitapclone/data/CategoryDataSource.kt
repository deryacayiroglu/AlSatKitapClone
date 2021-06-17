package com.cayiroglu.alsatkitapclone.data

import com.cayiroglu.alsatkitapclone.data.model.CategoryModel
import com.cayiroglu.alsatkitapclone.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    fun getCategories(): Flow<Resource<List<CategoryModel>>>
}