package com.cayiroglu.alsatkitapclone.ui.category

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cayiroglu.alsatkitapclone.data.model.CategoryModel
import com.cayiroglu.alsatkitapclone.databinding.ActivityCategoryBinding

import com.cayiroglu.alsatkitapclone.ui.products.ProductActivity
import com.cayiroglu.alsatkitapclone.util.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    var categoryViewModel = CategoryViewModel()
    private lateinit var categoryList:ArrayList<CategoryModel>
    private lateinit var progressDialog:ProgressDialog

    override fun onBackPressed() {
        AlertDialogUtil.showDialog(this,DIALOG_TYPE.CLOSE_DIALOG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        progressDialog = ProgressDialogUtil.showProgressDialog(this)
        search()
    }

    private fun initViewModel() {

        categoryViewModel.apply {

            categoriesLiveData.observe(this@CategoryActivity, Observer {

                categoryList = (it as ArrayList<CategoryModel>)

                it.run{
                    initRecycleView()
                    progressDialog.cancel()
                }

            })

            error.observe(this@CategoryActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@CategoryActivity, Observer {

            })

        }
    }

    private fun initRecycleView() {
        binding.apply {
            categoryAdapter = CategoryAdapter(categoryList, object : ItemClickListener {
                override fun onItemClick(position: Int) {

                }

                override fun onItemClick(clickedCategory: String) {
                    goToProductActivity(clickedCategory)
                }
            })
            rcvCategoryList.adapter = categoryAdapter
            rcvCategoryList.layoutManager = GridLayoutManager(applicationContext,Constants.GRID_SPAN)
        }
    }

    private fun search(){
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                categoryAdapter.filter.filter(query)
                categoryAdapter.notifyDataSetChanged()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                categoryAdapter.filter.filter(newText)
                categoryAdapter.notifyDataSetChanged()
                return false
            }
        })
    }

    private fun goToProductActivity(clickedCategory:String){
        val productIntent= Intent(this, ProductActivity::class.java)
        productIntent.putExtra(Constants.CLICKED_CATEGORY,clickedCategory)
        startActivity(productIntent)
    }

}