package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity

import com.emmanuel_rono.e_shoppa.Data.Database.productDao
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface

class ProductRepository(private val api: ApiInterface, private val productDao:productDao) {
    suspend fun getProducts(): List<ProductEntity> {
        val cachedProducts = productDao.getAllProducts()
        if (cachedProducts.isEmpty()) {
            // Fetch products from the API if not available in the cache
            val response = api.getAllProducts()
                val products = response.map{ it.toProductEntity() }
                productDao.InsertProduct(products)
            productDao.getAllProducts()
                return cachedProducts
            } else
            {
                return cachedProducts
            }
        }
        }


