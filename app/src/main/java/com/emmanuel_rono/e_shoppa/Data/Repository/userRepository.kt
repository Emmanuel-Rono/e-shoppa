package com.emmanuel_rono.e_shoppa.Data.Repository

import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class userRepository(private val apiService:ApiInterface)
{
        suspend fun login(email: String, password: String): Result<LoginResponse> {
            val loginRequest = login(email, password)
            val response = apiService.loginTheUser(loginRequest)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    return Result.success(loginResponse)
                }
            }
            return Result.failure(Exception("Login failed"))
        }

    object RetrofitClient {

        private const val BASE_URL = "https://fakestoreapi.com/"

    }


}

