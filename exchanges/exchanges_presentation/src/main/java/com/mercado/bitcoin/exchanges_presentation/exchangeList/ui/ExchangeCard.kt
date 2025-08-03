package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mercado.bitcoin.core.extensions.toDollarCurrency
import com.mercado.bitcoin.core_ui.theme.AppTheme
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_presentation.R

@Composable
fun ExchangeCard(data: ExchangeData, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardColors(
            containerColor = AppTheme.colors.white,
            contentColor = AppTheme.colors.white,
            disabledContainerColor = AppTheme.colors.white,
            disabledContentColor = AppTheme.colors.white
        ),
        border = BorderStroke(width = 2.dp, color = AppTheme.colors.primary),
        onClick = { onCardClick() }
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            ExchangeCardItem(
                label = stringResource(R.string.exchange_id_label),
                value = data.id
            )
            ExchangeCardItem(
                label = stringResource(R.string.exchange_name_label),
                value = data.name ?: stringResource(R.string.exchange_no_name)
            )
            ExchangeCardItem(
                label = stringResource(R.string.exchange_volume_label),
                value = data.volumePerDayUsd.toDollarCurrency()
            )
        }
    }
}

@Composable
fun ExchangeCardItem(label: String, value: String) {
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = AppTheme.typography.labelBold,
            color = AppTheme.colors.primary
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = value,
            style = AppTheme.typography.displayNormal,
            color = AppTheme.colors.primary
        )
    }
}