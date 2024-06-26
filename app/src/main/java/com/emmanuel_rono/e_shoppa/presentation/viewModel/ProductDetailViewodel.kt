package com.emmanuel_rono.e_shoppa.presentation.viewModel

import androidx.lifecycle.*
import com.emmanuel_rono.e_shoppa.Data.AllProducts.ProductEntity
import com.emmanuel_rono.e_shoppa.Domain.Repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailViewodel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<ProductEntity>()
    val product: LiveData<ProductEntity> get() = _product

    fun fetchProduct(productId: Int) {
        viewModelScope.launch {
            val product = repository.getProductById(productId)
            _product.postValue(product)
        }
    }
}
class ProductDetailViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailViewodel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

