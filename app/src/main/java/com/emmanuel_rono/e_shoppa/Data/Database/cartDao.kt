package com.emmanuel_rono.e_shoppa.Data.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
@Dao
interface cartDao
{
    @Query("SELECT * FROM CartProductTable ")
    suspend fun getSelectedProduct(): List<CartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun Insertproduct(product: com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity)
}
