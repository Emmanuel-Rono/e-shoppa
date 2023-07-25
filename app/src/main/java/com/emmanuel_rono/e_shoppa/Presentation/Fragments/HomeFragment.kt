package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emmanuel_rono.e_shoppa.Data.Database.AppDatabase
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.ProductViewModel
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Presentation.Adapters.ProductAdapter
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(emptyList())
        recyclerView = binding.homeRecyclerView
        recyclerView.adapter = adapter
        val appDatabase = AppDatabase.getInstance(requireContext())
        val productDao = appDatabase.productDao()
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val productRepository = ProductRepository(APiClient.apiService, productDao =productDao )
        val viewModelFactory = ProductViewModel.ProductViewModelFactory(productRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            adapter.products = products
            adapter.notifyDataSetChanged()
        })
        viewModel.getProducts()
    }
}
