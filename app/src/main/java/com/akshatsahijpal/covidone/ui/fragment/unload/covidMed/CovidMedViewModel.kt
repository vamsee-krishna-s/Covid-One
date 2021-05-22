package com.akshatsahijpal.covidone.ui.fragment.unload.covidMed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.repositories.unloadingRepo.CovidMedRepository

class CovidMedViewModel @ViewModelInject constructor(private val repo: CovidMedRepository) :
    ViewModel() {
    var covidMedData: LiveData<PagingData<CovidData>> =
        repo.getCovidMedData().cachedIn(viewModelScope)
}