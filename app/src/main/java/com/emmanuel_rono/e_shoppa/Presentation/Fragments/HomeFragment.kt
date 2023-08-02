package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Data.Database.AppDatabase
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Presentation.Adapters.ProductAdapter
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.ProductViewModel
import com.emmanuel_rono.e_shoppa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var  theAdapter: ProductAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appDatabase = AppDatabase.getInstance(requireContext())
        val productDao = appDatabase.productDao()
        val cartDao = appDatabase.cartDao()
        val productRepository = ProductRepository(
            APiClient.apiService, productDao = productDao,
            cartDao = cartDao
        )
        val viewModelFactory = ProductViewModel.ProductViewModelFactory(productRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        // Initialize adapter here with click listener
      theAdapter= ProductAdapter(listOf(), object : ProductAdapter.OnItemClickListener {
            override fun onItemClick(product: ProductEntity) {
                viewModel.insertProduct(product)
            }
      })
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = theAdapter
        }

        viewModel.products.observe(viewLifecycleOwner) { products ->
            theAdapter.products = products
            theAdapter.notifyDataSetChanged()
        }
        viewModel.getProducts()
    }
}
