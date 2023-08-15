package com.emmanuel_rono.e_shoppa.presentation.viewModel

import androidx.lifecycle.*
import com.emmanuel_rono.e_shoppa.Data.Login.LoginResponse
import com.emmanuel_rono.e_shoppa.Domain.Repository.UserRepository

import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Response<LoginResponse>>()
    val loginResult: LiveData<Response<LoginResponse>> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = userRepository.login(username, password)
            _loginResult.value = response
        }
    }
    class LoginViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(userRepository) as  T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}



