package com.cayiroglu.alsatkitapclone.ui.products

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.data.repository.ProductRepository
import com.cayiroglu.alsatkitapclone.util.ResourceStatus
import kotlinx.coroutines.launch

class ProductViewModel(categoryName:String) : ViewModel() {

    private  val productRepository: ProductRepository = ProductRepository()

    init {
        getProducts(categoryName)
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var productsLiveData = MutableLiveData<List<ProductModel>>()
    var error =    MutableLiveData<Throwable>()

    fun getProducts(categoryName: String) = viewModelScope.launch {
        productRepository.getProducts(categoryName)
                .asLiveData(viewModelScope.coroutineContext).observeForever {
                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            productsLiveData.postValue(it.data!!)
                            loading?.postValue(false)
                        }

                        ResourceStatus.ERROR -> {
                            error.postValue(it.throwable!!)
                            loading?.postValue(false)
                        }
                    }
                }
    }


}