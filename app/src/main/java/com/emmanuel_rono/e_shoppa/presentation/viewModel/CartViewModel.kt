package com.emmanuel_rono.e_shoppa.presentation.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Domain.Repository.cartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private var repository: cartRepository):ViewModel()
{
    private var _cartItem= MutableLiveData<List<CartEntity>>()
    private val cartItem:MutableLiveData<List<CartEntity>> get() =  _cartItem
    init {
        getCartItem()

    }
fun getCartItem() {
    viewModelScope.launch {
        val item = repository.getCartItem()
        withContext(Dispatchers.Main) {
            cartItem.value = item
        }
    }
}
    fun deleteItemsAll() {
        viewModelScope.launch {
         repository.deleteAll()
            fetchCartItems()
        }
    }

        fun deleteItem(productId:Int)
    {
        viewModelScope.launch {
            repository.deleteProduct(productId)
            fetchCartItems()
        }
    }
    fun updateQuantity(id: Int, quantity: Int) {
        viewModelScope.launch {
            repository.updateQuantity(id, quantity)
            fetchCartItems()
        }}



    class CartViewmodelFactory(private val repository: cartRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                return CartViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun fetchCartItems() {
        viewModelScope.launch {
            _cartItem.postValue(repository.getCartItem())
        }
    }
}