package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.emmanuel_rono.e_shoppa.databinding.FragmentLoginBinding

class SplashScren : Fragment() {
    private var _binding:FragmentLoginBinding? =null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

    }
}


