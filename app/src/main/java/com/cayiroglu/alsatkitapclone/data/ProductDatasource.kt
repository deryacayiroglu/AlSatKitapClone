package com.cayiroglu.alsatkitapclone.data

import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductDatasource {
    fun getProducts(categoryName: String): Flow<Resource<List<ProductModel>>>
}