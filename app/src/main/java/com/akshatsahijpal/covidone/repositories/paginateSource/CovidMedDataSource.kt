package com.akshatsahijpal.covidone.repositories.paginateSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.db.remote.unload.dataSource.FetchFireData

class CovidMedDataSource constructor(private val data: FetchFireData) : PagingSource<Int, CovidData>() {
    override fun getRefreshKey(state: PagingState<Int, CovidData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CovidData> {
        val position = params.key ?: 1
        return try {
            val response =  data.generateDataSet()
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}