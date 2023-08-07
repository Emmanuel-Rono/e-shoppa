package com.emmanuel_rono.e_shoppa.Presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Domain.Repository.profileRepository
import kotlinx.coroutines.launch

class profileViewodel(private var repository: profileRepository): ViewModel() {

    var _userProfile = MutableLiveData<profileBio>()
     val userProfile:LiveData<profileBio> get() = _userProfile

    fun getUserProfile(){
        //actual call
        viewModelScope.launch {
            val user=repository.getProfileData()
            _userProfile.value=user
        }
    }
    class profilewmodelFactory(private val repository: profileRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(profileViewodel::class.java)) {
                return profileViewodel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

