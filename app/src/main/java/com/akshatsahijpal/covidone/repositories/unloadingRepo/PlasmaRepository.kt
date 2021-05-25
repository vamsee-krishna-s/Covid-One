package com.akshatsahijpal.covidone.repositories.unloadingRepo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.db.local.RunDAO
import com.akshatsahijpal.covidone.db.remote.unload.dataSource.FetchPlasmaData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlasmaRepository @Inject constructor(
    private val db: FetchPlasmaData,
    private val dao: RunDAO
) {

/*    fun getCovidMedData() =
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
*/

    fun getSearchResult(query: String):
            LiveData<PagingData<CovidData>> {
        GlobalScope.launch{
            db.generateDataSet()
        }
        return Pager(
            PagingConfig(
                pageSize = 5,
                maxSize = 50,
                enablePlaceholders = true
            )
        ) {
            dao.getSearchResult(query)
        }.liveData
    }
}