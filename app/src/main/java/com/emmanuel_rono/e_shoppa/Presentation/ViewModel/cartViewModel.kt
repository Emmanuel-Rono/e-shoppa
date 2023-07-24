package com.emmanuel_rono.e_shoppa.Presentation.ViewModel

import android.text.Editable.Factory
import androidx.lifecycle.*
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItem
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartProductsItemEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import com.emmanuel_rono.e_shoppa.Domain.ApiInterface
import com.emmanuel_rono.e_shoppa.Domain.Repository.CartProductRepository
import kotlinx.coroutines.launch

class cartViewModel(val repository:CartProductRepository) : ViewModel()
{


    val products: MutableLiveData<List<CartProductsItemEntity>> = MutableLiveData()

    val _cartItem= MutableLiveData<List<CartProductsItemEntity>>()
    val cartItem:LiveData<List<CartProductsItemEntity>> get() = _cartItem
    fun getItems()
    {
        val api= APiClient.apiService
        viewModelScope.launch {
            try {
                val fetchedItems=repository.getCartProducts()
                _cartItem.value=fetchedItems
            }
            catch (e:Exception){e.printStackTrace()}
        }
    }
}
class CartProductViewModelProviderFactory(private val repository: CartProductRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(cartViewModel::class.java)) {
            return cartViewModel(repository ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
