package com.mercado.bitcoin.exchanges_presentation

import app.cash.turbine.test
import arrow.core.Either
import com.mercado.bitcoin.core.exceptions.ApiException
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.core.network.getErrorApiExceptionOrNull
import com.mercado.bitcoin.exchanges_domain.model.CurrencyInfo
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeSharedRepository
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenEvent
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel.ExchangeDetailsScreenViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeDetailsScreenViewModelTest {

    private lateinit var exchangeRepository: ExchangeRepository
    private lateinit var sharedRepository: ExchangeSharedRepository
    private lateinit var viewModel: ExchangeDetailsScreenViewModel

    private val exchangeData = ExchangeData(
        id = 123,
        name = "Binance",
        description = "Top exchange",
        logo = "binance.png",
        spotVolumeUSD = 12345.0,
        dateLaunched = null,
        makeFee = null,
        takerFee = null,
        website = "https://binance.com"
    )

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        exchangeRepository = mockk()
        sharedRepository = mockk()
    }

    @Test
    fun `initial state should emit exchange details when exchangeData is available`() = runTest {
        val exchangeFlow = MutableStateFlow(exchangeData)
        val eitherSuccess = Either.Right(
            listOf(CurrencyInfo("BTC", 115000.0), CurrencyInfo("ETH", 3200.0))
        )

        coEvery { sharedRepository.getCurrentExchangeData() } returns exchangeFlow
        coEvery { exchangeRepository.getExchangeDetails("123") } returns eitherSuccess

        viewModel = ExchangeDetailsScreenViewModel(exchangeRepository, sharedRepository)

        val deferred = async {
            exchangeFlow.first()
        }

        exchangeFlow.emit(exchangeData)
        viewModel.state.test {
            skipItems(1)

            assertEquals(exchangeData, deferred.await())
            val loading = awaitItem()
            assertEquals(LoadingEvent.Loading, loading.listCurrencyLoadingEvent)
            val success = awaitItem()
            assertEquals(LoadingEvent.Success(eitherSuccess.value), success.listCurrencyLoadingEvent)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `if getExchangeDetails return error state should be set to error `() = runTest {
        val exchangeFlow = MutableStateFlow(exchangeData)
        val eitherError = Either.Left(ApiException.UnexpectedException(Throwable()))

        coEvery { sharedRepository.getCurrentExchangeData() } returns exchangeFlow
        coEvery { exchangeRepository.getExchangeDetails("123") } returns eitherError

        viewModel = ExchangeDetailsScreenViewModel(exchangeRepository, sharedRepository)

        val deferred = async {
            exchangeFlow.first()
        }


        exchangeFlow.emit(exchangeData)
        viewModel.state.test {
            skipItems(1)

            val state = awaitItem()
            assertEquals(exchangeData, deferred.await())
            val loadingEvent = state.listCurrencyLoadingEvent
            assertEquals(LoadingEvent.Loading, loadingEvent)
            val error = awaitItem()
            assertEquals(LoadingEvent.Error(eitherError.value), error.listCurrencyLoadingEvent)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `onEvent RetryInitialCall should reload data`() = runTest {
        val exchangeFlow = MutableStateFlow(exchangeData)
        val eitherSuccess = Either.Right(listOf(CurrencyInfo("BTC", 115000.0)))

        every { sharedRepository.getCurrentExchangeData() } returns exchangeFlow
        coEvery { exchangeRepository.getExchangeDetails("123") } returns eitherSuccess

        viewModel = ExchangeDetailsScreenViewModel(exchangeRepository, sharedRepository)

        viewModel.state.test {
            skipItems(1)

            viewModel.onEvent(ExchangeDetailsScreenEvent.RetryInitialCall)

            val loading = awaitItem()
            assertTrue(loading.listCurrencyLoadingEvent is LoadingEvent.Loading)

            val success = awaitItem()
            assertTrue(success.listCurrencyLoadingEvent is LoadingEvent.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
