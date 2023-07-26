package com.emmanuel_rono.e_shoppa.Data.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItemEntity

@Dao
interface CartDao {
    @Query("Select * from cardItemTable")
    suspend fun getCartItems():List<CartProductsItemEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertCartItems(item:List<CartProductsItemEntity>)
}