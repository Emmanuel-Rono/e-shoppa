package com.emmanuel_rono.e_shoppa.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItemEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.databinding.ItemBinding

class cartItemAdapter(private val catItem:List<ProductEntity>):RecyclerView.Adapter<cartItemAdapter.viewHolder>()
{
    inner class viewHolder(private val binding:ItemBinding):RecyclerView.ViewHolder(binding.root){
        val Itemimage=binding.cartItemImage
        val Itemname=binding.cartItemName
        val ItemPrice=binding.cartItemPrice
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflator= LayoutInflater.from(parent.context)
        val binding= ItemBinding.inflate(inflator,parent,false)
        return viewHolder(binding)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item=catItem[position]
        holder.Itemname.text=item.title
        holder.ItemPrice.text=item.price
        Glide.with(holder.Itemimage)
            .load(item.image)
            .into(holder.Itemimage)

    }
    override fun getItemCount(): Int {
        return catItem.size
    }
}