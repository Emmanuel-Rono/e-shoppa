package com.emmanuel_rono.e_shoppa.Data.AllProducts

data class profileBio(
    val address: Address,
    val email: String,
    val name: Name,
    val phone: String,
    val id: Int?,
    val username: String
)