package com.cayiroglu.alsatkitapclone.ui.products

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cayiroglu.alsatkitapclone.R
import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.databinding.ActivityProductBinding

import com.cayiroglu.alsatkitapclone.ui.detail.DetailActivity
import com.cayiroglu.alsatkitapclone.util.Constants
import com.cayiroglu.alsatkitapclone.util.ItemClickListener
import com.cayiroglu.alsatkitapclone.util.ObjectUtil
import com.cayiroglu.alsatkitapclone.util.ProgressDialogUtil

class ProductActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityProductBinding
    private lateinit var productAdapter: ProductAdapter
    lateinit var productViewModel:ProductViewModel
    var productList = ArrayList<ProductModel>()
    var clickedCategory:String ?= null
    private var isGridLayout:Boolean = false
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickedCategory = intent.getStringExtra(Constants.CLICKED_CATEGORY).toString()
        productViewModel = ProductViewModel(clickedCategory!!)

        progressDialog = ProgressDialogUtil.showProgressDialog(this)

        binding.apply {
            txtCategory.text = clickedCategory
            btnLayoutChange.setOnClickListener(this@ProductActivity)
            btnSort.setOnClickListener(this@ProductActivity)
        }

        initViewModel()
    }

    private fun initViewModel() {

        productViewModel.apply {

            productsLiveData.observe(this@ProductActivity, Observer {

                productList = it as ArrayList<ProductModel>

                it.run {
                    initRecyclerView()
                    progressDialog.cancel()
                }

            })

            error.observe(this@ProductActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@ProductActivity, Observer {

            })

        }
    }

    private fun initRecyclerView(){
        binding.apply {
            productAdapter = ProductAdapter(productList,object : ItemClickListener{
                override fun onItemClick(position: Int) {
                    val clickedProduct:ProductModel = productList[position]
                    goToDetailActivity(clickedProduct)
                }

                override fun onItemClick(clickedCategory: String) {

                }

            })
            rcvProductList.adapter = productAdapter
            if (isGridLayout){
                rcvProductList.layoutManager = GridLayoutManager(applicationContext,Constants.GRID_SPAN)
            }else{
                rcvProductList.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
            }
        }

    }

    private fun layoutChange(){
        binding.apply {
            if(isGridLayout){
                btnLayoutChange.text = resources.getString(R.string.text_grid)
                btnLayoutChange.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.icon_grid_32),null,null,null)
                isGridLayout = false
                initRecyclerView()
            }
            else{
                btnLayoutChange.text = resources.getString(R.string.text_liste)
                btnLayoutChange.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.icon_linear_32),null,null,null)
                isGridLayout = true
                initRecyclerView()
            }
        }
    }

    private fun changeSort(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.alert_dialog_sort_title))
        builder.setItems(resources.getStringArray(R.array.alert_dialog_sort)) { dialog, position ->
            when (position) {
                Constants.SORT_BY_NAME -> {
                    productList.sortBy { it.bookName }
                    productAdapter.notifyDataSetChanged()
                }
                Constants.SORT_BY_DESCENDING_NAME -> {
                    productList.sortByDescending { it.bookName }
                    productAdapter.notifyDataSetChanged()
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun goToDetailActivity(productModel: ProductModel){
        val productIntent= Intent(this, DetailActivity::class.java)
        val clickedProduct:String = ObjectUtil.objectToString(productModel)
        productIntent.putExtra(Constants.CLICKED_PRODUCT,clickedProduct)
        startActivity(productIntent)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLayoutChange->
            {
                layoutChange()
            }
            R.id.btnSort->
            {
                changeSort()
            }
        }
    }

}