package com.emmanuel_rono.e_shoppa.Presentation.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Domain.Repository.cartRepository
import kotlinx.coroutines.launch

class cartViewModel(private var repository: cartRepository):ViewModel()
{
    var _cartItem= MutableLiveData<List<CartEntity>>()
    val CartItem:MutableLiveData<List<CartEntity>> get() =  _cartItem
fun getCartItem(){
    viewModelScope.launch {
        val item = repository.getCartItem()
       CartItem.value=item
    }
}

    class cartviewmodelFactory(private val repository: cartRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(cartViewModel::class.java)) {
                return cartViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}