package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.Database.cartDao

class cartRepository(var dao: cartDao) {
    suspend fun getCartItem(): List<CartEntity> {
        return dao.getSelectedProduct()
    }
}