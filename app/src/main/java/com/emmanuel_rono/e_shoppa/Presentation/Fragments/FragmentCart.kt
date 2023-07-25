package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.Database.AppDatabase

import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Domain.Repository.CartProductRepository

import com.emmanuel_rono.e_shoppa.Presentation.Adapters.cartItemAdapter
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.cartViewModel
import com.emmanuel_rono.e_shoppa.databinding.FragmentCartBinding

class FragmentCart : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var CartItemAdapter: cartItemAdapter
    private lateinit var binding : FragmentCartBinding
    private lateinit var viewModel: cartViewModel
    private val productDetailsList = mutableListOf<ProductEntity>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
         binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.cartRecyclerView
        setupRecyclerView()
        val appDatabase = AppDatabase.getInstance(requireContext())
        val cartDao = appDatabase.cartDao()
        val cartProductRepository = CartProductRepository(APiClient.apiService, cartDao)
      //  val viewModelFactory = (cartProductRepository)
        val viewModelFactory =
            cartViewModel.CartProductViewModelProviderFactory(cartProductRepository)
        viewModel = ViewModelProvider(this)[cartViewModel::class.java]
        setupObservers()
        viewModel.getItems()
    }

    private fun setupRecyclerView() {
        CartItemAdapter = cartItemAdapter(productDetailsList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CartItemAdapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers() {
        viewModel.cartItem.observe(viewLifecycleOwner) { cartItems ->
            cartItems?.let {
                productDetailsList.clear()
                for (cartItem in cartItems) {
                    for (product in cartItem.products) {
                        // Create a new product and add it to the list
                        val productEntity = ProductEntity(
                            id = product.id,
                            price = product.price,
                            image = product.image,
                            title = product.title
                        )
                        productDetailsList.add(productEntity)
                    }
                }
                CartItemAdapter.notifyDataSetChanged()
            }
        }
    }
}
