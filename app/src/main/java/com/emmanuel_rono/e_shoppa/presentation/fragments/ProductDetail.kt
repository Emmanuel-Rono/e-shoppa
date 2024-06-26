package com.emmanuel_rono.e_shoppa.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.FragmentHomeBinding
import com.emmanuel_rono.e_shoppa.databinding.FragmentLoginBinding
import com.emmanuel_rono.e_shoppa.databinding.FragmentProductDetailBinding


class productDetail : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    }
