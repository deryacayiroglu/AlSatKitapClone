package com.cayiroglu.alsatkitapclone.data.remote

import android.util.Log
import com.cayiroglu.alsatkitapclone.data.ProductDatasource
import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.util.Constants
import com.cayiroglu.alsatkitapclone.util.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RemoteProductDataSource : ProductDatasource {
    override fun getProducts(categoryName: String): Flow<Resource<List<ProductModel>>> = callbackFlow {

        try {

            offer(Resource.Loading())

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
            val myRef: Query = database.child(Constants.PRODUCT_PATH).orderByChild(Constants.CATEGORYNAME_PATH).equalTo(categoryName)

            val productList = arrayListOf<ProductModel>()

            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (postSnapshoot in dataSnapshot.getChildren()) {

                        val item = postSnapshoot.getValue(ProductModel::class.java)!!
                        productList.add(item)

                    }

                    offer(Resource.Success(productList))
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            awaitClose {  }

        } catch (e: Exception) {
            offer(Resource.Error(e))
            e.printStackTrace()
        }

    }
}