package com.cayiroglu.alsatkitapclone.data.remote

import android.util.Log
import com.cayiroglu.alsatkitapclone.data.CategoryDataSource
import com.cayiroglu.alsatkitapclone.data.model.CategoryModel
import com.cayiroglu.alsatkitapclone.util.Constants
import com.cayiroglu.alsatkitapclone.util.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RemoteCategoryDataSource : CategoryDataSource {

    override fun getCategories(): Flow<Resource<List<CategoryModel>>> = callbackFlow {

        try {

            offer(Resource.Loading())

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
            val myRef: DatabaseReference = database.child(Constants.CATEGORY_PATH)

            val categoryList = arrayListOf<CategoryModel>()

            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (postSnapshoot in dataSnapshot.getChildren()) {

                        val item = postSnapshoot.getValue(CategoryModel::class.java)!!
                        categoryList.add(item)

                    }

                    offer(Resource.Success(categoryList))
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