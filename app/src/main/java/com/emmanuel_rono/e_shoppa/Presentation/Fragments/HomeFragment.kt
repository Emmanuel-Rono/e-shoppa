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
import com.emmanuel_rono.e_shoppa.Data.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Data.ViewModel.ProductViewModel
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Presentation.Adapters.ProductAdapter
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(rootView)
        return rootView
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= ProductAdapter(emptyList())
        recyclerView = binding.homeRecyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        //val products = getProductsFromApi() //
        val userRepository = ProductRepository(APiClient.apiService)
        val viewModelFactory = ProductViewModel.ProductViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            adapter.products = products
            adapter.notifyDataSetChanged()
        })

        viewModel.getProducts()
    }
}


