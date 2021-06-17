package com.cayiroglu.alsatkitapclone.ui.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cayiroglu.alsatkitapclone.data.model.CategoryModel
import com.cayiroglu.alsatkitapclone.data.repository.CategoryRepository
import com.cayiroglu.alsatkitapclone.util.ResourceStatus
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {

    private  val categoryRepository: CategoryRepository = CategoryRepository()

    init {
        getCategories()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var categoriesLiveData = MutableLiveData<List<CategoryModel>>()
    var error =    MutableLiveData<Throwable>()

    fun getCategories()  = viewModelScope.launch {

        categoryRepository.getCategories()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        categoriesLiveData.postValue(it.data!!)
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