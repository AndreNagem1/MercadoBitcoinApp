package com.mercado.bitcoin.core.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.convertToStringUI() =
    this.format(DateTimeFormatter.ofPattern("d/MM/uuuu"))
