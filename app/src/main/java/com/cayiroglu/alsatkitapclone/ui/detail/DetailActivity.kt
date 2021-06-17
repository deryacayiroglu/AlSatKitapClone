package com.cayiroglu.alsatkitapclone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cayiroglu.alsatkitapclone.R
import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.databinding.ActivityDetailBinding
import com.cayiroglu.alsatkitapclone.databinding.ActivityProductBinding
import com.cayiroglu.alsatkitapclone.util.Constants
import com.cayiroglu.alsatkitapclone.util.ObjectUtil
import com.cayiroglu.alsatkitapclone.util.getImage

class DetailActivity : AppCompatActivity() {

    var clickedProductString:String ?= null
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init(){
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickedProductString = intent.getStringExtra(Constants.CLICKED_PRODUCT)
        val productModel: ProductModel = ObjectUtil.jsonStringToObject(clickedProductString.toString())

        binding.apply {
            txtBookNameDetail.text = productModel.bookName
            imgDetail.getImage(productModel.imageUrl.toString())
            txtPriceDetail.text = productModel.price
            txtRatingDetail.text = productModel.rating
            txtAuthorDetail.text = productModel.author
            txtPublisherDetail.text = productModel.publisher
            txtExplanation.text = productModel.explanation
            txtNumberOfPages.text = productModel.numberOfPages
            txtState.text = productModel.bookState
            productModel.rating!!.toFloat().also { ratingBarDetail.rating = it }
        }

    }
}