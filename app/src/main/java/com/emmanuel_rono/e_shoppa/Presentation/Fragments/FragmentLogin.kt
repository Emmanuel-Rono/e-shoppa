package com.emmanuel_rono.e_shoppa.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emmanuel_rono.e_shoppa.Domain.Repository.UserRepository
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.LoginViewModel
import com.emmanuel_rono.e_shoppa.Domain.APiClient.apiService
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.Utils.validateDetails

import com.emmanuel_rono.e_shoppa.databinding.FragmentLoginBinding


class fragmentLogin : Fragment() {
    lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = _binding!!.loginUsername.editableText.toString()
        val password = _binding!!.loginPassword.editableText.toString()
        _binding?.loginButton?.setOnClickListener {
            val userDetails = validateDetails(username, password)
            if (userDetails.valid) {
                _binding!!.loginProgress.visibility = View.VISIBLE
                _binding!!.loginButton.isEnabled = false
                val userRepository = UserRepository(apiService)
                val viewModelFactory = LoginViewModel.LoginViewModelFactory(userRepository)
                viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
                viewModel.login(username,password)
                viewModel.loginResult.observe(this) { response ->
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            val token = loginResponse.token
                            // Login successful, navigate to the next screen
                            Toast.makeText(requireContext(), "Success",Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.homeFragment)
                            }
                    } else {
                        val errorBody = response.errorBody()
                        // Handle login error
                    }
                }

            }
        }
    }
}