package com.emmanuel_rono.e_shoppa.Domain

import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    //Login
    @POST("auth/login")
    suspend fun loginTheUser(@Body login: Response<LoginResponse>): Response<LoginResponse>

}