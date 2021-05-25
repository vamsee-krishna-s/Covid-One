package com.akshatsahijpal.covidone.ui.fragment.unload.oxygen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.repositories.unloadingRepo.OxygenRepository

class OxygenViewModel @ViewModelInject constructor(private val repo: OxygenRepository) :
    ViewModel() {
    private var currentQuery = MutableLiveData(DEFAULT_QUERY)
    fun getDataSetr(): LiveData<PagingData<CovidData>> {
        return currentQuery.switchMap { queryVal ->
            repo.getSearchResult(queryVal).cachedIn(viewModelScope)
        }
    }

    fun searchModel(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "ALL"
    }
}