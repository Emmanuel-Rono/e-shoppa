package com.emmanuel_rono.e_shoppa.Data.Repository

import com.emmanuel_rono.e_shoppa.Data.Login.Login
import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface

class userRepository(private val apiService:ApiInterface)
{
        suspend fun login(email: String, password: String): Result<LoginResponse> {
            val loginRequest = Login(email, password)
            val response = apiService.loginTheUser(loginRequest)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    return Result.success(loginResponse)
                }
            }
            return Result.failure(Exception("Login failed"))
        }

    }

