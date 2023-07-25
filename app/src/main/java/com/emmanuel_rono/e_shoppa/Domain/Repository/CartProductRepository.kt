package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItem
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface

class CartProductRepository (private val api: ApiInterface){
    suspend fun getCartProducts(): List<CartProductsItem> {
        return api.getCartItems()
    }

}