package com.emmanuel_rono.e_shoppa.Data.AllProducts

data class Address(
    val city: String,
    val geolocation: Geolocation,
    val number: Int,
    val street: String,
    val zipcode: String
)