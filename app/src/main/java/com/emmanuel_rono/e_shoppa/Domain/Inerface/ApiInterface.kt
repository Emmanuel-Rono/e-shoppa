package com.emmanuel_rono.e_shoppa.Domain.Inerface


import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Data.Login.Login
import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    //Login
    @POST("auth/login")
    suspend fun loginTheUser(@Body login: Login): Response<LoginResponse>
    @GET("/products")
    suspend fun getAllProducts(): List<Products>

    @GET("/users/1")
    suspend fun getUserProf():List<profileBio>
}