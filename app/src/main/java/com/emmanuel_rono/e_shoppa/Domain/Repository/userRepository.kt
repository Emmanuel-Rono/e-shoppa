package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Data.Login.Login
import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import com.emmanuel_rono.e_shoppa.Domain.Inerface.ApiInterface
import retrofit2.Response

class UserRepository(private val api: ApiInterface) {

    suspend fun login(username: String, password: String): Response<LoginResponse> {

        val loginRequest = Login(username,password)

        return api.loginTheUser(loginRequest)

    }

}

