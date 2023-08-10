package com.emmanuel_rono.e_shoppa.Presentation.Fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.Database.AppDatabase
import com.emmanuel_rono.e_shoppa.Domain.Repository.cartRepository
import com.emmanuel_rono.e_shoppa.Presentation.Adapters.cartItemAdapter
import com.emmanuel_rono.e_shoppa.Presentation.ViewModel.cartViewModel
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.FragmentCartBinding
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener


class FragmentCart : Fragment() {
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

            thecartItemAdapter = cartItemAdapter(mutableListOf(),object : cartItemAdapter.OnItemClickListener {
                override fun onItemClick(product: CartEntity) {
                   Toast.makeText(context,"Coming soon",Toast.LENGTH_SHORT).show()
                }
        },object : cartItemAdapter.OnDeleteClickListener {
                override fun onDeleteClick(product: CartEntity) {
                    viewmodel.deleteItem(product.id)
                }
            },object : cartItemAdapter.OnPlusClickListener {
                override fun onPlusClick(product: CartEntity) {
                }
                override fun increaseQuantity(id: Int, quantity: Int) {
                    viewmodel.updateQuantity(id, quantity)}
            },object : cartItemAdapter.OnMinusClickListener {
                override fun onMinusClick(product: CartEntity) {
                }
                override fun reduceQuantity(id: Int, quantity: Int) {
                    viewmodel.updateQuantity(id, quantity)
                }
            })
        binding.cartRecyclerView.apply {
            adapter = thecartItemAdapter
            layoutManager = GridLayoutManager(requireContext(), 1)
        }
        viewmodel.CartItem.observe(viewLifecycleOwner) { products ->
            thecartItemAdapter.updateList(products)
        }

        viewmodel.getCartItem()
        viewmodel.fetchCartItems()
        binding.cartBack.setOnClickListener() {findNavController().navigate(R.id.homeFragment)}
        binding.cartClearAll.setOnClickListener() {viewmodel.deleteItemsAll()}


        binding.cartCheckout.setOnClickListener() {
            PopupDialog.getInstance(context)
                .setStyle(Styles.ALERT)
                .setHeading("On Implementation")
                .setDescription(
                    "Please Check back " +
                            " soon.Thankyou ! "
                )
                .setCancelable(false)
                .showDialog(object : OnDialogButtonClickListener() {
                    override fun onDismissClicked(dialog: Dialog?) {
                        super.onDismissClicked(dialog)
                    }
                })
        }

}}

