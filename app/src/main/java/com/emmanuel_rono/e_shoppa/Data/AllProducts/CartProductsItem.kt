package com.emmanuel_rono.e_shoppa.Data.AllProducts

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CartProductsItem(
    val date: String,
    val id: Int,
    val products: List<Products>,
    val userId: Int
)