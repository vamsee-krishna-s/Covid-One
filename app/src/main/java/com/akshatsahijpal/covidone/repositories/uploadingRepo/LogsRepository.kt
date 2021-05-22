package com.akshatsahijpal.covidone.repositories.uploadingRepo

import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.db.remote.upload.FirebaseUpload
import javax.inject.Inject

class LogsRepository @Inject constructor(private var src: FirebaseUpload) {
    fun uploadData(user: CovidData, dropDownList: Array<String>) {
        src.upload(user, dropDownList)
    }
}