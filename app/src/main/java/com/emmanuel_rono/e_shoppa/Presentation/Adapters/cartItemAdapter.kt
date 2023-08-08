package com.emmanuel_rono.e_shoppa.Presentation.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.cartViewModel
import com.emmanuel_rono.e_shoppa.R

import com.emmanuel_rono.e_shoppa.databinding.ItemBinding

class cartItemAdapter(
    var list: MutableList<CartEntity>,
    private val clickListener: OnItemClickListener,
    private val deleteListener: OnDeleteClickListener,
    private val plusListener: OnPlusClickListener,
    private val minusListener: OnMinusClickListener
) : RecyclerView.Adapter<cartItemAdapter.viewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(product: CartEntity)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(product: CartEntity)
    }

    interface OnPlusClickListener {
        fun onPlusClick(product: CartEntity)
        fun increaseQuantity(id: Int, quantity: Int)
    }

    interface OnMinusClickListener {
        fun onMinusClick(product: CartEntity)
        fun reduceQuantity(id:Int,quantity: Int)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<CartEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged() // Notify the RecyclerView to update
    }

    inner class viewHolder(private val binding: ItemBinding, private val deleteListener: OnDeleteClickListener, private val plusListener: OnPlusClickListener, private val minusListener: OnMinusClickListener) : RecyclerView.ViewHolder(binding.root) {
        val Itemimage = binding.cartItemImage
        val Itemname = binding.cartItemName
        val ItemPrice = binding.cartItemPrice
        val itemQuantity = binding.cartItemQuantity

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(list[position])
                }
            }
            itemView.findViewById<ImageView>(R.id.cart_item_delete).setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteListener.onDeleteClick(list[position])
                }
            }

            itemView.findViewById<ImageView>(R.id.cart_item_plus).setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val ItemAmt=list[position]
                    ItemAmt.quantity++
                    binding.cartItemQuantity.text = ItemAmt.quantity.toString()
                    plusListener.onPlusClick(list[position])
                    plusListener.increaseQuantity(ItemAmt.id,ItemAmt.quantity)
                }
            }

            itemView.findViewById<ImageView>(R.id.cart_item_minus).setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val itemAmount=list[position]

                    itemAmount.quantity--
                    binding.cartItemQuantity.text=itemAmount.quantity.toString()
                    minusListener.onMinusClick(list[position])
                    minusListener.reduceQuantity(itemAmount.id,itemAmount.quantity)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartItemAdapter.viewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflator, parent, false)
        return viewHolder(binding, deleteListener, plusListener, minusListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = list[position]
        holder.Itemname.text = item.title
        holder.itemQuantity.text=item.quantity.toString()
        holder.ItemPrice.text = item.price.toString()
        Glide.with(holder.Itemimage)
            .load(item.image)
            .into(holder.Itemimage)
    }


    override fun getItemCount(): Int {
        return list.size
    }


}





