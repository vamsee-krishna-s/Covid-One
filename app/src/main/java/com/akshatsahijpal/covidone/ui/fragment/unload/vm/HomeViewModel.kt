package com.akshatsahijpal.covidone.ui.fragment.unload.vm

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.repositories.localRepo.MainRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepository
) : ViewModel() {
    private var allRemoteData: MutableLiveData<ArrayList<CovidData>> = MutableLiveData()
    private var allLocalData: MutableLiveData<List<CovidData>> = MutableLiveData()
    fun insertToLocalDB(user: CovidData) {
        viewModelScope.launch { mainRepo.insertRun(user) }
    }

    fun allFromLocal() {
        var set = mainRepo.getAllData()
        allLocalData.postValue(set)
    }

    fun getAllFromLocal(): MutableLiveData<List<CovidData>> {
        return this.allLocalData
    }

    fun allDataFromRemote() {
        GlobalScope.async {
            val data = mainRepo.getFirebaseData()
            allRemoteData.postValue(data)
            Log.d("res->", "res->$data")
        }
    }

    fun getAllDataFromRemote(): MutableLiveData<ArrayList<CovidData>> {
        return this.allRemoteData
    }
}