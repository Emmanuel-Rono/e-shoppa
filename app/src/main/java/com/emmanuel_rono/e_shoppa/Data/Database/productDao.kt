package com.emmanuel_rono.e_shoppa.Data.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity

@Dao
interface  productDao {
    @Query("Select * From products")
    suspend fun  getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertProduct(products: List<ProductEntity>)

    //Retrieves Details
    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: Int): ProductEntity
}