package com.emmanuel_rono.e_shoppa.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emmanuel_rono.e_shoppa.Data.Repository.UserRepository
import com.emmanuel_rono.e_shoppa.Data.ViewModel.LoginViewModel
import com.emmanuel_rono.e_shoppa.Domain.APiClient.apiService
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.Utils.validateDetails
import com.emmanuel_rono.e_shoppa.Utils.Result
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

        _binding?.loginButton?.setOnClickListener {
            val username = _binding!!.loginUsername.editableText.toString()
            val password = _binding!!.loginPassword.editableText.toString()

            val userDetails = validateDetails(username, password)
            if (userDetails.valid) {
                _binding!!.loginProgress.visibility = View.VISIBLE
                _binding!!.loginButton.isEnabled = false

                val userRepository = UserRepository(apiService)
                viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

                viewModel.loginResult.observe(this) { response ->
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            val token = loginResponse.token
                            // Login successful, navigate to the next screen
                            findNavController().navigate(R.id.SecondFragment)
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