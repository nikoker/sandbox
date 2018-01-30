package com.getbux.sandbox

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.getbux.sandbox.domain.ProductController


class MainFragmentViewModel(productId: String) : ViewModel() {

    // Inject
    val productController = ProductController()
    var backgroundColor = ObservableField<Int>()


    val product = productController.getProduct(productId)

    class Factory(private val productId: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainFragmentViewModel(productId) as T
        }
    }
}