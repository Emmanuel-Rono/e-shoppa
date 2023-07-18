package com.emmanuel_rono.e_shoppa.Domain.Repository

import CartProducts
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItem
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItemEntity
import com.emmanuel_rono.e_shoppa.Data.Database.CartDao
import com.emmanuel_rono.e_shoppa.Data.Database.productDao
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface

class CartProductRepository (val api: ApiInterface,val dao:CartDao){
    suspend fun getCartProducts():List<CartProductsItemEntity>
    {
        val cachedProductItems=dao.getCartItems()
        if (cachedProductItems.isEmpty()) {
            val cartItem =api.getCartItems()
            //Map the items to EntityClass
            val cartItems=cartItem.map { it.toCartproductsEnity() }
            dao.InsertCartItems(cartItems)
            dao.getCartItems()
            return cachedProductItems
        }
        return cachedProductItems
    }
}