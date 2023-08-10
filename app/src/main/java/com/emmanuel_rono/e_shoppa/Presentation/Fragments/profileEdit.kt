package com.emmanuel_rono.e_shoppa.Domain.Repository.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.FragmentProfileEditBinding


class profileEdit : Fragment() {
    lateinit var binding : FragmentProfileEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentProfileEditBinding.inflate(inflater,container,false)
        return binding.root
    }

}