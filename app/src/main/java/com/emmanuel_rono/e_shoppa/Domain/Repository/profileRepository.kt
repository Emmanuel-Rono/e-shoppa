package com.emmanuel_rono.e_shoppa.Domain.Repository

import android.provider.ContactsContract.Profile
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.Inerface.ApiInterface

class profileRepository(val api:ApiInterface)
{
     suspend fun getProfileData():List<profileBio> {
        return api.getUserProf()
    }

}