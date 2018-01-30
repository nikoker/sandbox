package com.getbux.sandbox.domain

import java.math.BigDecimal

data class Product(val id: String, val name: String, val price: BigDecimal) {
    val priceString: String
        get() = price.toPlainString()
}
