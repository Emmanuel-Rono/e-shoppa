package com.emmanuel_rono.e_shoppa.presentation.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.R

import com.emmanuel_rono.e_shoppa.databinding.ItemBinding

class CartItemAdapter(
    var list: MutableList<CartEntity>,
    private val clickListener: OnItemClickListener,
    private val deleteListener: OnDeleteClickListener,
    private val plusListener: OnPlusClickListener,
    private val minusListener: OnMinusClickListener
) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {


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

    inner class ViewHolder(private val binding: ItemBinding, private val deleteListener: OnDeleteClickListener, private val plusListener: OnPlusClickListener, private val minusListener: OnMinusClickListener) : RecyclerView.ViewHolder(binding.root) {
        val itemimage = binding.cartItemImage
        val itemname = binding.cartItemName
        val itemPrice = binding.cartItemPrice
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
                    val itemAmt=list[position]
                    itemAmt.quantity++
                    binding.cartItemQuantity.text = itemAmt.quantity.toString()
                    plusListener.onPlusClick(list[position])
                    plusListener.increaseQuantity(itemAmt.id,itemAmt.quantity)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemAdapter.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflator, parent, false)
        return ViewHolder(binding, deleteListener, plusListener, minusListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.itemname.text = item.title
        holder.itemQuantity.text=item.quantity.toString()
        holder.itemPrice.text = item.price.toString()
        Glide.with(holder.itemimage)
            .load(item.image)
            .into(holder.itemimage)
    }


    override fun getItemCount(): Int {
        return list.size
    }


}





