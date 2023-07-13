package com.emmanuel_rono.e_shoppa.Data.ViewModel

import androidx.lifecycle.*
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products
import com.emmanuel_rono.e_shoppa.Data.Repository.ProductRepository
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) :ViewModel(){
    private val _Products = MutableLiveData<List<Products>>()
    val  products: LiveData<List<Products>> get() = _Products
 fun getProducts()
 {
     val apiService=APiClient.apiService
     viewModelScope.launch{
         try{
             val fetchedProducts=repository.getProducts()
             _Products.value=fetchedProducts
         }
         catch (e:Exception){
             e.printStackTrace()
     }}
 }
    class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                return ProductViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }}


