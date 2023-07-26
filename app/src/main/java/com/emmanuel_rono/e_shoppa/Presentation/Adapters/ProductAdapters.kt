package com.emmanuel_rono.e_shoppa.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products
import com.emmanuel_rono.e_shoppa.databinding.ProductBinding

class ProductAdapter (var products:List<ProductEntity>):RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding:ProductBinding):RecyclerView.ViewHolder(binding.root)
    {
val image=binding.itemImage
val price=binding.itemPrice
val reviews=binding.itemReview

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflator=LayoutInflater.from(parent.context)
        val binding=ProductBinding.inflate(inflator,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val product=products[position]
        holder.price.text=product.price
        Glide.with(holder.itemView)
            .load(product.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}