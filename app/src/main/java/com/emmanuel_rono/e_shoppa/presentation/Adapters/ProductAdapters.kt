package com.emmanuel_rono.e_shoppa.presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.databinding.ProductBinding

class ProductAdapter(
    var products: List<ProductEntity>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.itemImage
        val price = binding.itemPrice
        val reviews = binding.itemReview
        val title=binding.itemTitle

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(products[position])
                }
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(product: ProductEntity)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ProductBinding.inflate(inflator, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.price.text = product.price.toString()
        holder.title.text=product.title
        Glide.with(holder.itemView)
            .load(product.image)
            .into(holder.image)
    }
    override fun getItemCount(): Int {
        return products.size
    }
}
