package com.akshatsahijpal.covidone.repositories

import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.db.local.RunDAO
import com.akshatsahijpal.covidone.db.remote.FetchFireData
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDao: RunDAO,
    private val fetch: FetchFireData
) {
    suspend fun insertRun(run: CovidData) = runDao.insertRun(run)

    suspend fun deleteRun(run: CovidData) = runDao.deleteRun(run)

    fun getAllData() = runDao.getAllData()

    suspend fun getFirebaseData(): ArrayList<CovidData> {
        return fetch.generateDataSet()
    }
}