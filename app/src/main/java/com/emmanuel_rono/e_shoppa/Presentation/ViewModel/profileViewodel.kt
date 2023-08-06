package com.emmanuel_rono.e_shoppa.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Domain.Repository.profileRepository

class profileViewodel(private var repository: profileRepository): ViewModel() {

    var _userProfile = MutableLiveData<List<profileBio>>()

     val userProfile:MutableLiveData<List<profileBio>> get() = _userProfile

    suspend fun getUserProfile(): List<profileBio> {
        //actual call
       val user=repository.getProfileData()
        userProfile.value=user
return user
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

