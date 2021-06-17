package com.cayiroglu.alsatkitapclone.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cayiroglu.alsatkitapclone.data.model.ProductModel
import com.cayiroglu.alsatkitapclone.databinding.CardviewProductBinding
import com.cayiroglu.alsatkitapclone.util.ItemClickListener
import com.cayiroglu.alsatkitapclone.util.getImage


class ProductAdapter(var products: ArrayList<ProductModel>, var onItemClickListener: ItemClickListener) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardviewProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardviewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {

                txtBookName.text = products[position].bookName
                imgBook.getImage(products[position].imageUrl.toString())
                txtAuthor.text = products[position].author
                txtPrice.text = products[position].price
                txtPublisher.text = products[position].publisher
                txtRating.text = products[position].rating
                txtSeller.text = products[position].seller

                ratingBar.rating = products[position].rating!!.toFloat()

                constraintLayout.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }

            }
        }
    }


}