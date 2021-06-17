package com.cayiroglu.alsatkitapclone.data.repository

import com.cayiroglu.alsatkitapclone.data.ProductDatasource
import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.data.remote.RemoteProductDataSource
import com.cayiroglu.alsatkitapclone.util.Resource
import kotlinx.coroutines.flow.Flow

class ProductRepository {
    private var productDataSource: ProductDatasource?=null

    init {
        productDataSource= RemoteProductDataSource()
    }

    fun getProducts(categoryName:String): Flow<Resource<List<ProductModel>>>
    {
        return productDataSource!!.getProducts(categoryName)
    }
}