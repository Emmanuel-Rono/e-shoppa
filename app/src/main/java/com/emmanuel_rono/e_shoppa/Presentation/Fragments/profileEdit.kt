package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Address
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Name
import com.emmanuel_rono.e_shoppa.Data.AllProducts.profileBio
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Domain.Repository.profileRepository
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.profileViewodel
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.Utils.ProfileBioResult
import com.emmanuel_rono.e_shoppa.databinding.FragmentProfileEditBinding


class profileEdit : Fragment() {
    lateinit var binding: FragmentProfileEditBinding
    lateinit var viewodel: profileViewodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        val viewModelFactory =
            profileViewodel.profilewmodelFactory(profileRepository(APiClient.apiService))
        viewodel = ViewModelProvider(this, viewModelFactory)[profileViewodel::class.java]
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edtBack.setOnClickListener() { findNavController().navigate(R.id.profileFragment) }

        var currentUserId: Int? = null
        viewodel.userProfile.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ProfileBioResult.Loading
                -> {
                    // binding.loadingProgress.visibility=View.VISIBLE
                    Toast.makeText(context, "Loading..", Toast.LENGTH_LONG).show()
                }

                is ProfileBioResult.Success -> {

                    currentUserId = result.profileBio.id

                    //binding.loadingProgress.visibility=View.INVISIBLE
                    val user = result.profileBio
                    binding.editProfileEmailAddress.setText(user.email)
                    binding.editProfileFirstName.setText(user.name.firstname)
                    binding.editProfileLastName.setText(user.name.lastname)
                    binding.editProfileUsername.setText(user.username)
                    binding.editProfilePhoneNumber.setText(user.phone)
                    binding.editProfileAddress.setText(user.address.city)
                }
                else -> {
                    Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewodel.userProfile
        viewodel.getUserProfile()

        fun getCurrentUserId(): Int {
            return currentUserId ?: -1
        }
        binding.updateProfButton.setOnClickListener() {
            val userId = getCurrentUserId()
            if (userId != -1) { // Check if a valid user ID is available
                val updatedProfile = profileBio(
                    email = binding.editProfileEmailAddress.text.toString(),
                    name = Name(
                        firstname = binding.editProfileFirstName.text.toString(),
                        lastname = binding.editProfileLastName.text.toString()
                    ),
                    username = binding.editProfileUsername.text.toString(),
                    phone = binding.editProfilePhoneNumber.text.toString(),
                    address = Address(
                        city = binding.editProfileAddress.text.toString()
                    ),
                id=null
                )
                    viewodel.Updateprofile(userId,updatedProfile)
            } else {
                Toast.makeText(context, "Error: User ID not found", Toast.LENGTH_SHORT).show()
            }

        }


        }

    }
