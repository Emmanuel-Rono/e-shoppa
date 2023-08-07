package com.emmanuel_rono.e_shoppa.Domain.Repository

import android.provider.ContactsContract.Profile
import androidx.lifecycle.LiveData
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.Inerface.ApiInterface
import okhttp3.Call

class profileRepository(val api:ApiInterface)
{
     suspend fun getProfileData(): profileBio {
        return api.getUserProf()
    }

}