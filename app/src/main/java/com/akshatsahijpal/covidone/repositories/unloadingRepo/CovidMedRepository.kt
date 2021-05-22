package com.akshatsahijpal.covidone.repositories.unloadingRepo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.akshatsahijpal.covidone.db.remote.unload.dataSource.FetchFireData
import com.akshatsahijpal.covidone.repositories.paginateSource.CovidMedDataSource
import javax.inject.Inject

// Repo -> PagingData
class CovidMedRepository @Inject constructor(private val db: FetchFireData) {

    fun getCovidMedData() =
        Pager(
            PagingConfig(
                pageSize = 5,
                maxSize = 50,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                CovidMedDataSource(db)
            }
        ).liveData
}