package com.mercado.bitcoin.core.extensions

import com.mercado.bitcoin.core.exceptions.CurrencyFormatException
import java.text.NumberFormat
import java.util.Locale

fun Double.toDollarCurrency(): String {
    if (!this.isFinite()) throw CurrencyFormatException("Cannot format NaN or Infinity")

    return try {
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.format(this)
    } catch (e: Exception) {
        throw CurrencyFormatException("Formatting failed for $this", e)
    }
}
