package com.akshatsahijpal.covidone.repositories

import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.db.remote.FirebaseUpload
import javax.inject.Inject

class LogsRepository @Inject constructor(private var src: FirebaseUpload) {
    fun uploadData(user: CovidData) {
        src.upload(user)
    }
}