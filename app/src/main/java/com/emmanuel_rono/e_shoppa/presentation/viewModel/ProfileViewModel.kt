package com.emmanuel_rono.e_shoppa.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.Repository.profileRepository
import com.emmanuel_rono.e_shoppa.utils.ProfileBioResult
import kotlinx.coroutines.launch

class ProfileViewModel(private var repository: profileRepository): ViewModel() {

    var _userProfile = MutableLiveData<ProfileBioResult>()
     val userProfile:LiveData<ProfileBioResult> get() = _userProfile

    fun getUserProfile() {
        //actual call
        viewModelScope.launch {
            val user = repository.getProfileData()
            _userProfile.value = user

            _userProfile.value = ProfileBioResult.Loading
        }
    }

        fun updateprofile(userId: Int, user: profileBio)
        {


            viewModelScope.launch {
                    _userProfile.value=ProfileBioResult.Loading
                    val response = repository.UpdateProf(userId, user)
                    _userProfile.value =response
                }

            }


    class ProfileViewModelFactory(private val repository: profileRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

