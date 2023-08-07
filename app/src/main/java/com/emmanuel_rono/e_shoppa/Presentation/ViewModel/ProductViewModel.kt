package com.emmanuel_rono.e_shoppa.Presentation.ViewModel

import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import androidx.lifecycle.*
import com.emmanuel_rono.e_shoppa.Data.AllProducts.CartEntity
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity


import com.emmanuel_rono.e_shoppa.Domain.APiClient
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) :ViewModel(){
    private val _Products = MutableLiveData<List<ProductEntity>>()
    val  products: MutableLiveData<List<ProductEntity>> get() = _Products
 fun getProducts()
 {
     val apiService=APiClient.apiService
     viewModelScope.launch{
         try{
             val fetchedProducts = repository.getProducts()
             _Products.value=fetchedProducts
         }
         catch (e:Exception){
             e.printStackTrace()
     }}
 }
    fun insertProduct(product: CartEntity) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }
    class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                return ProductViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }}


