package com.emmanuel_rono.e_shoppa.Data.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.Login.Login
import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import com.emmanuel_rono.e_shoppa.Data.Repository.userRepository
import kotlinx.coroutines.launch

class loginViewModel(private val repository: userRepository): ViewModel()
//variable to hold livedata
{
val _loginResult = MutableLiveData<Result<LoginResponse>>()
    //get()-> provide the value of _loginResut
    val loginResult:LiveData<Result<LoginResponse>> get() = _loginResult


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            _loginResult.value = result
        }
    }

}