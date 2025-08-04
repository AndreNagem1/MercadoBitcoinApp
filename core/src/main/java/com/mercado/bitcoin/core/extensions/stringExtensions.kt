package com.mercado.bitcoin.core.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.mercado.bitcoin.core.exceptions.ParseLocalDateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val String.Companion.EMPTY: String
    get() = ""


@RequiresApi(Build.VERSION_CODES.O)
fun String?.convertToLocalDate(pattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSSX"): LocalDate? {

    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        java.time.OffsetDateTime.parse(this, formatter).toLocalDate()
    } catch (e: Exception) {
        throw ParseLocalDateTimeException("Parsing local date failed for $this", e)
    }

}

fun String?.getShortDescription(): String? {
    if (this.isNullOrBlank()) {
        return null
    }

    if (this.length > 100) {
        return this.substring(0, 100)
    }

    return this
}