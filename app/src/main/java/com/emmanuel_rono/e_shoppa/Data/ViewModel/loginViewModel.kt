package com.emmanuel_rono.e_shoppa.Data.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.Login.Login
import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import com.emmanuel_rono.e_shoppa.Data.Repository.UserRepository

import kotlinx.coroutines.launch
import retrofit2.Response


class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Response<LoginResponse>>()
    val loginResult: LiveData<Response<LoginResponse>> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = userRepository.login(email, password)
            _loginResult.value = response
        }
    }
}


