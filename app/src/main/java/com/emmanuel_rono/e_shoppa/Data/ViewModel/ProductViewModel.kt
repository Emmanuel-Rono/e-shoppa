package com.emmanuel_rono.e_shoppa.Data.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanuel_rono.e_shoppa.Data.AllProducts.Products
import com.emmanuel_rono.e_shoppa.Data.Repository.UserRepository
import com.emmanuel_rono.e_shoppa.Domain.APiClient
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel(private val repository: UserRepository) :ViewModel(){

    private val _Products=MutableLiveData<List<Products>>()
    val  products: LiveData<List<Products>> get() = _Products
 fun getProducts()
 {
     val apiService=APiClient.apiService
     viewModelScope.launch{
         try{
             val fetchedProducts=apiService.getAllProducts()
             _Products.value=fetchedProducts
         }
         catch (e:Exception){
             e.printStackTrace()
     }}
 }


}