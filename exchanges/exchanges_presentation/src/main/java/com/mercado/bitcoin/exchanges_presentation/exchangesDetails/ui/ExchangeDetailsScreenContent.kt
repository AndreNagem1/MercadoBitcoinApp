package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mercado.bitcoin.core.extensions.convertToStringUI
import com.mercado.bitcoin.core.extensions.getShortDescription
import com.mercado.bitcoin.core.extensions.toDollarCurrency
import com.mercado.bitcoin.core.network.getErrorApiExceptionOrNull
import com.mercado.bitcoin.core.network.getSuccessDataOrNull
import com.mercado.bitcoin.core.network.isError
import com.mercado.bitcoin.core.network.isLoading
import com.mercado.bitcoin.core_ui.composables.BaseScreen
import com.mercado.bitcoin.core_ui.theme.AppTheme
import com.mercado.bitcoin.exchanges_presentation.R
import com.mercado.bitcoin.exchanges_presentation.exchangeList.ui.ExchangeCardItem
import com.mercado.bitcoin.exchanges_presentation.exchangeList.ui.ExchangeLogoImage
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenEvent
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExchangeDetailsScreenContent(
    state: ExchangeDetailsScreenState,
    onEvent: (ExchangeDetailsScreenEvent) -> Unit,
) {
    BaseScreen(
        screenTitle = stringResource(R.string.exchange_details_screen_title),
        isLoading = state.listCurrencyLoadingEvent.isLoading(),
        isError = state.listCurrencyLoadingEvent.isError(),
        error = state.listCurrencyLoadingEvent.getErrorApiExceptionOrNull(),
        retryInitialCall = { onEvent(ExchangeDetailsScreenEvent.RetryInitialCall) }
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 10.dp, bottom = 60.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    val data = state.exchangeData
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ExchangeLogoImage(
                            imageUrl = data?.logo.orEmpty()
                        )
                    }
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_name_label),
                        value = data?.name ?: stringResource(R.string.exchange_no_name)
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_id_label),
                        value = data?.id.toString()
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_description_label),
                        value = data?.description?.getShortDescription()
                            ?: stringResource(R.string.exchange_no_description)
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_website_label),
                        value = data?.website ?: stringResource(R.string.exchange_not_informed)
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_spot_volume_label),
                        value = data?.spotVolumeUSD?.toDollarCurrency()
                            ?: stringResource(R.string.exchange_not_informed)
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_maker_fee_label),
                        value = data?.makeFee?.toDollarCurrency()
                            ?: stringResource(R.string.exchange_not_informed)
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_taker_fee_label),
                        value = data?.takerFee?.toDollarCurrency()
                            ?: stringResource(R.string.exchange_not_informed)
                    )
                    ExchangeCardItem(
                        label = stringResource(R.string.exchange_date_launched_label),
                        value = data?.dateLaunched?.convertToStringUI()
                            ?: stringResource(R.string.exchange_not_informed)
                    )

                    Text(
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                        style = AppTheme.typography.labelBold,
                        color = AppTheme.colors.primary,
                        text = "Crypto List: ",
                    )
                }
            }

            state.listCurrencyLoadingEvent.getSuccessDataOrNull()?.let { currencyList ->
                if (currencyList.isEmpty()) {
                    item {
                        Text(text = "Crypto list is empty")
                    }
                }

                items(currencyList.size) { index ->
                    val currency = currencyList[index]
                    Text(
                        text = currency.name,
                        style = AppTheme.typography.labelBold,
                    )
                    Text(text = currency.priceUSD.toDollarCurrency())
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

    }
}