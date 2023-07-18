package com.emmanuel_rono.e_shoppa.Data.AllProducts

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CartProductsItem(
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
) {
    //Create Mapping
//Cartpro..Entity-Is not receiving remote data
//Map from cart..items

    fun toCartproductsEnity(): CartProductsItemEntity {
        return CartProductsItemEntity(date,id,products,userId)
    }
}

@Entity(tableName = "cardItemTable")
data class CartProductsItemEntity(
    val date: String,
    @PrimaryKey val id: Int,
    val products: List<Product>,
    val userId: Int
)