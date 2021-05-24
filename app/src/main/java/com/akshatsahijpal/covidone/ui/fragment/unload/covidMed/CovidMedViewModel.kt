package com.akshatsahijpal.covidone.ui.fragment.unload.covidMed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.repositories.unloadingRepo.CovidMedRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CovidMedViewModel @ViewModelInject constructor(private val repo: CovidMedRepository) :
    ViewModel() {
/*    var covidMedData: LiveData<PagingData<CovidData>> =
        repo.getCovidMedData().cachedIn(viewModelScope)*/
    private var currentQuery = MutableLiveData(DEFAULT_QUERY)
    fun getDataSetr(): LiveData<PagingData<CovidData>> {
        return currentQuery.switchMap { queryVal -> repo.getSearchResult(queryVal).cachedIn(viewModelScope) }
    }

    fun searchModel(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "Delhi"
    }
}