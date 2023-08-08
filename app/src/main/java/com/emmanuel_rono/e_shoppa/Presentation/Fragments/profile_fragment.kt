package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Domain.Repository.profileRepository
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.profileViewodel
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.Utils.ProfileBioResult
import com.emmanuel_rono.e_shoppa.databinding.FragmentProfileBinding

class profile_fragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var viewodel: profileViewodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val viewModelFactory = profileViewodel.profilewmodelFactory(profileRepository( APiClient.apiService))
        viewodel = ViewModelProvider(this, viewModelFactory)[profileViewodel::class.java]
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observe the results
        viewodel.userProfile.observe(viewLifecycleOwner) { result ->
         when(result) {
             is ProfileBioResult.Loading
             -> {
                // binding.loadingProgress.visibility=View.VISIBLE
                Toast.makeText(context, "Loading..", Toast.LENGTH_LONG).show()
             }

            is ProfileBioResult.Success -> {
                //binding.loadingProgress.visibility=View.INVISIBLE
                val user=result.profileBio
                binding.profileEmail.text = user.email // for example
                binding.profileName.text = "${user.name.firstname} ${user.name.lastname}"

            }
            else -> {
                Toast.makeText(context,"No connection",Toast.LENGTH_SHORT).show()
            }
            }}

        viewodel.getUserProfile()

        //Setting Onclick Listeners for the other components of Profile Fragment
        binding.profileEdit.setOnClickListener { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        val notifications=binding.profileNotifications
        val wishList=binding.profileWishlist
        val termsofuse=binding.profileTerms
        val privacy=binding.profilePrivacy
        val report=binding.profileReportBug
        val Logout=binding.profileLogout


        notifications.setOnClickListener() { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        wishList.setOnClickListener() { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        termsofuse.setOnClickListener() { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        privacy.setOnClickListener() { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        report.setOnClickListener() { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        Logout.setOnClickListener() { Toast.makeText(context,"Check back Soon",Toast.LENGTH_SHORT).show() }
        binding.profileBack.setOnClickListener() { findNavController().navigate(R.id.homeFragment)}



    }}