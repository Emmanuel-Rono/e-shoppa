package com.emmanuel_rono.e_shoppa.Data.AllProducts

import androidx.room.Entity
import androidx.room.PrimaryKey
 data class Products(
     val category: String,
     val description: String,
     val id: Int,
     val image: String,
     val price: Double,
     val title: String,
     val productId: Int,
     val quantity: Int
 )
{
   fun toProductEntity():ProductEntity{
        return ProductEntity(id,title,price,image,quantity)
    }

}
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int
)
{
    fun toCartEntity():CartEntity{
        return CartEntity(id,title,price,image,quantity)
}

}
@Entity(tableName ="CartProductTable")
data class CartEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    var quantity: Int
)


//For Product Detail
