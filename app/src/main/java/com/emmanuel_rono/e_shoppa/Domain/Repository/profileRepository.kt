package com.emmanuel_rono.e_shoppa.Domain.Repository

import com.emmanuel_rono.e_shoppa.Domain.Inerface.ApiInterface
import com.emmanuel_rono.e_shoppa.Utils.ProfileBioResult


class profileRepository(val api:ApiInterface)
{
    suspend fun getProfileData(): ProfileBioResult {
        return try {
            val profileBio = api.getUserProf()
            ProfileBioResult.Success(profileBio)
        } catch (e: Exception) {
            ProfileBioResult.Error(e)
        }
    }
}
