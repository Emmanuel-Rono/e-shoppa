package com.emmanuel_rono.e_shoppa.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
@Database(entities = [ProductEntity::class,CartEntity::class ],version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): productDao
    abstract fun cartDao(): cartDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database").fallbackToDestructiveMigration().build()
        }
    }
}
