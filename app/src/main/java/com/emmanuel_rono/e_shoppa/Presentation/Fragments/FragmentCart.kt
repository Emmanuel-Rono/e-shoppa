package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.Database.AppDatabase
import com.emmanuel_rono.e_shoppa.Data.Database.productDao
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Domain.Repository.cartRepository
import com.emmanuel_rono.e_shoppa.Presentation.Adapters.ProductAdapter
import com.emmanuel_rono.e_shoppa.Presentation.Adapters.cartItemAdapter
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.ProductViewModel
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.cartViewModel

import com.emmanuel_rono.e_shoppa.databinding.FragmentCartBinding
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewmodel: cartViewModel
    private lateinit var thecartItemAdapter: cartItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appDatabase = AppDatabase.getInstance(requireContext())
        val cartDao = appDatabase.cartDao()
        val productRepository = cartRepository (  dao = cartDao)
        val viewModelFactory = cartViewModel.cartviewmodelFactory(productRepository)
        viewmodel = ViewModelProvider(this, viewModelFactory)[cartViewModel::class.java]
        binding.cartRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            thecartItemAdapter = cartItemAdapter(mutableListOf())
            adapter =thecartItemAdapter
        }
        viewmodel.CartItem.observe(viewLifecycleOwner) { products ->
            thecartItemAdapter.catItem = products
            thecartItemAdapter.notifyDataSetChanged()
        }
        viewmodel.getCartItem()
    }

}

