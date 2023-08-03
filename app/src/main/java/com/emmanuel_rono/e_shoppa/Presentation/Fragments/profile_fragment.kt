package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Domain.Repository.profileRepository
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.ProductViewModel
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.cartViewModel
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.profileViewodel
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.FragmentProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class profile_fragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var viewodel: profileViewodel
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewodel = ViewModelProvider(this)[profileViewodel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observe the results
        scope.launch {
            viewodel.getUserProfile()
            viewodel.userProfile.observe(viewLifecycleOwner) { result ->
                if (result.isNotEmpty()) {
                    binding.profileEmail.text = result[0].email.toString() // for example
                    binding.profileName.text = result[0].name.toString()// for example

                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Cancel the job when the Fragment is destroyed
    }
}