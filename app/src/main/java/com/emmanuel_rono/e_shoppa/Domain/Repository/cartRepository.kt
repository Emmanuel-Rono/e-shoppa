package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.Database.cartDao

class cartRepository(var dao: cartDao) {
    suspend fun getCartItem(): List<CartEntity> {
        return dao.getSelectedProduct()
    }
    suspend fun deleteAll() {
        return dao.DeleteAll()
    }
    suspend fun deleteProduct(productId:Int) {
        return dao.deleteProduct(productId)
    }
    suspend fun updateQuantity(id: Int, quantity: Int) {
        dao.updateQuantity(id, quantity)
    }
}
