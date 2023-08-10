package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.Inerface.ApiInterface
import com.emmanuel_rono.e_shoppa.Utils.ProfileBioResult


class profileRepository(val api:ApiInterface) {
    suspend fun getProfileData(): ProfileBioResult {
        return try {
            val profileBio = api.getUserProf()
            ProfileBioResult.Success(profileBio)
        } catch (e: Exception) {
            ProfileBioResult.Error(e)
        }
    }

    suspend fun UpdateProf(userId: Int, user: profileBio): ProfileBioResult {
      try {
            val newUser = api.updateUser(userId, user)
            if (newUser.isSuccessful) {
                newUser.body()?.let {
                    return ProfileBioResult.Success(it)
                } ?: run {
                    return ProfileBioResult.Error(Exception("Failed to parse the updated user"))
                }
            } else {
                return ProfileBioResult.Error(Exception("Update failed with code: ${newUser.code()}"))
            }
        } catch (e: Exception) {
            return ProfileBioResult.Error(e)
        }
    }
}
