package com.mercado.bitcoin.exchanges_presentation.exchangeList.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_presentation.exchangeList.useCase.GetExchangeListUseCase

class PagingSource(
    val useCase: GetExchangeListUseCase
) : PagingSource<Int, ExchangeData>() {

    override fun getRefreshKey(state: PagingState<Int, ExchangeData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExchangeData> {
        val position = params.key ?: STARTING_PAGE_INDEX

        var loadResult: LoadResult<Int, ExchangeData> =
            LoadResult.Error(Exception())

        useCase(currentPage = position).collect {
            loadResult = when (it) {
                is LoadingEvent.Error -> LoadResult.Error(it.exception)
                is LoadingEvent.Success -> {
                    LoadResult.Page(
                        data = it.data,
                        prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
                        nextKey = if (it.data.isEmpty()) null else position + 1
                    )
                }

                else -> {
                    LoadResult.Page(
                        data = listOf(),
                        prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
                        nextKey = null
                    )
                }
            }
        }

        return loadResult
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}