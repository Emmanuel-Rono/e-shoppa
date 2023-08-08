package com.emmanuel_rono.e_shoppa.Utils

import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio


sealed class ProfileBioResult {
    object Loading : ProfileBioResult()
    data class Success(val profileBio: profileBio) : ProfileBioResult()
    data class Error(val exception: Exception) : ProfileBioResult()
}
