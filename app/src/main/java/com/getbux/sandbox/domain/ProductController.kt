package com.getbux.sandbox.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.math.BigDecimal
import java.util.*

class ProductController {

    private val productLiveDatas = mutableMapOf<String, MutableLiveData<Product>>()
    private val productQuoteGenerators = mutableMapOf<String, Job>()

    private val random = Random()

    fun getProduct(id: String): LiveData<Product> = productLiveDatas.getOrPut(id) {
        ProductLiveData(id)
    }

    private inner class ProductLiveData(private val id: String) : MutableLiveData<Product>() {
        override fun onActive() = startObservingProduct(id)
        override fun onInactive() = stopObservingProduct(id)
    }

    private fun startObservingProduct(id: String) {
        println("Subscribing to $id")

        productQuoteGenerators[id] = launch(UI) {
            delay(1500L)

            val liveData = productLiveDatas.get(id) ?: return@launch
            liveData.postValue(Product(id, id, BigDecimal.ONE))

            while (isActive) {
                delay(500L)
                liveData.postValue(Product(id, id, BigDecimal.valueOf(0.5 + random.nextDouble())))
            }
        }
    }

    private fun stopObservingProduct(id: String) {
        println("Unsubscribing from $id")

        productQuoteGenerators.remove(id)?.cancel()
    }

}