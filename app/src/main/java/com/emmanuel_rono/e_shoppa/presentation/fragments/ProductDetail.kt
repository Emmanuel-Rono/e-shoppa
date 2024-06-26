package com.emmanuel_rono.e_shoppa.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emmanuel_rono.e_shoppa.databinding.ProductDetailScreenBinding


class productDetail : Fragment() {
    private var _binding: ProductDetailScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ProductDetailScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    }
