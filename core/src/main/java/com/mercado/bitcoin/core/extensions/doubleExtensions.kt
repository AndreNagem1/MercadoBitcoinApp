package com.mercado.bitcoin.core.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toDollarCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this)
}