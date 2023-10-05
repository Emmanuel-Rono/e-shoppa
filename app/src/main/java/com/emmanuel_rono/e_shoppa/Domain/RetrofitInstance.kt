package com.emmanuel_rono.e_shoppa.Domain

import com.emmanuel_rono.e_shoppa.Domain.Inerface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APiClient {
    private val BASE_URL = "https://fakestoreapi.com/"

    //build the client

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

    }
