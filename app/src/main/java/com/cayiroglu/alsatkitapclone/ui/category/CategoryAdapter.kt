package com.cayiroglu.alsatkitapclone.ui.category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable

import androidx.recyclerview.widget.RecyclerView
import com.cayiroglu.alsatkitapclone.data.model.CategoryModel
import com.cayiroglu.alsatkitapclone.databinding.CardviewCategoryBinding
import com.cayiroglu.alsatkitapclone.util.ItemClickListener
import com.cayiroglu.alsatkitapclone.util.getImage


class CategoryAdapter(var categories: ArrayList<CategoryModel>, var onItemClickListener: ItemClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), Filterable {

    private var filteredCategories:ArrayList<CategoryModel> = categories

    inner class ViewHolder(val binding: CardviewCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardviewCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredCategories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {

                txtCategoryName.text = filteredCategories[position].categoryName
                imgCategory.getImage(filteredCategories[position].imageUrl.toString())

                categoryCard.setOnClickListener {
                    onItemClickListener.onItemClick(filteredCategories[position].categoryName.toString())
                }

            }
        }
    }

    override fun getFilter(): android.widget.Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                if(constraint.isNullOrEmpty()){
                    filteredCategories = categories
                }
                else{

                    val searchString = constraint.toString().toLowerCase()
                    val tempFilteredList = ArrayList<CategoryModel>()

                    for (category in filteredCategories){
                        if(category.categoryName!!.toLowerCase().contains(searchString)){
                            tempFilteredList.add(category)
                        }
                    }
                    filteredCategories = tempFilteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredCategories
                filterResults.count = filteredCategories.count()
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredCategories = results?.values as ArrayList<CategoryModel>
                notifyDataSetChanged()
            }

        }
    }

}