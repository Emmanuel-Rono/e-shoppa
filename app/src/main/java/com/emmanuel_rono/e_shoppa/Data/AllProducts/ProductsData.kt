package com.emmanuel_rono.e_shoppa.Data.AllProducts

import androidx.room.Entity
import androidx.room.PrimaryKey

 data class Products(
    val category: String,
    val description: String,
  val id: String,
    val image: String,
    val price: String,
    val title: String
)
{
   fun toProductEntity():ProductEntity{
        return ProductEntity(id,title,price,image)
    }

}
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val title: String,
    val price: String,
    val image: String

)
