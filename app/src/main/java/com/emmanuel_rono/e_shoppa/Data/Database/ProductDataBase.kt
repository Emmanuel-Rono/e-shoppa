package com.emmanuel_rono.e_shoppa.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItemEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products

@Database(entities = [ProductEntity::class, CartProductsItemEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): productDao
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                .build()
        }
    }
}
