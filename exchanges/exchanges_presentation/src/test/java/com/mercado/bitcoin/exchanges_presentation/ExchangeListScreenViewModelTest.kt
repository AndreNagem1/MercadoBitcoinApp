package com.mercado.bitcoin.exchanges_presentation

import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeSharedRepository
import com.mercado.bitcoin.exchanges_presentation.exchangeList.paging.ExchangeListFactory
import com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel.ExchangeListScreenViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeListScreenViewModelTest {

    private lateinit var sharedRepository: ExchangeSharedRepository
    private lateinit var exchangeListFactory: ExchangeListFactory

    private lateinit var viewModel: ExchangeListScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `saveExchangeData should call repository with correct data`() = runTest {
        val data = ExchangeData(
            id = 2915,
            name = null,
            description = "prompta",
            logo = "disputationi",
            spotVolumeUSD = 18.19,
            dateLaunched = null,
            makeFee = null,
            takerFee = null,
            website = null
        )

        sharedRepository = mockk(relaxUnitFun = true)
        exchangeListFactory = mockk()
        every { exchangeListFactory.invoke() } returns emptyFlow()

        viewModel = ExchangeListScreenViewModel(exchangeListFactory, sharedRepository)

        viewModel.saveExchangeData(data)

        coVerify {
            sharedRepository.setCurrentExchangeData(
                coroutineScope = any(),
                exchangeData = data
            )
        }
    }

}
