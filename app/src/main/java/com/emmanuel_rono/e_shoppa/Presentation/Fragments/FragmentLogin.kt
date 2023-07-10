package com.emmanuel_rono.e_shoppa.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emmanuel_rono.e_shoppa.Utils.validateDetails
import com.emmanuel_rono.e_shoppa.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class fragmentLogin : Fragment() {

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

        _binding?.loginButton?.setOnClickListener{
            val username  = _binding!!.loginUsername.editableText.toString()
            val password= _binding!!.loginPassword.editableText.toString()

            val userDetails=validateDetails(username,password)
if (userDetails.valid)
{
    _binding!!.loginProgress.visibility=View.VISIBLE
    _binding!!.loginButton.isEnabled=false

}
        }
    }
}