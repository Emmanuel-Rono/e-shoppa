package com.emmanuel_rono.e_shoppa.Data.Repository

import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface


class ProductRepository(private val apiService: ApiInterface) {
    suspend fun getProducts(): List<Products> {
        return apiService.getAllProducts()
    }
}