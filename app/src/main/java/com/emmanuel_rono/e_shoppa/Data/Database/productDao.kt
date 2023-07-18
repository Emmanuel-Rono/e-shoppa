package com.emmanuel_rono.e_shoppa.Data.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products

@Dao
interface  productDao {
    @Query("Select * From products")
    suspend fun  getAllProducts(): List<ProductEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertProduct(products: List<ProductEntity>)
}