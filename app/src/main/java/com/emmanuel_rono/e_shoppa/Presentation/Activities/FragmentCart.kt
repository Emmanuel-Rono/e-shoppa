package com.emmanuel_rono.e_shoppa.Presentation.Activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.emmanuel_rono.e_shoppa.databinding.FragmentCartBinding

class FragmentCart : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

}