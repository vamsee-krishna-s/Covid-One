package com.akshatsahijpal.covidone.ui.fragment.upload.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.repositories.LogsRepository

class ContributeViewModel @ViewModelInject constructor(private var repo: LogsRepository): ViewModel() {
    fun upload(usr: CovidData, dropDownList: Array<String>){
        repo.uploadData(usr, dropDownList)
    }
}