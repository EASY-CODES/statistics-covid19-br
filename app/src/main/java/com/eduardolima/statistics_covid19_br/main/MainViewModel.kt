package com.eduardolima.statistics_covid19_br.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eduardolima.statistics_covid19_br.data.model.State
import com.eduardolima.statistics_covid19_br.data.model.Total
import com.eduardolima.statistics_covid19_br.data.repository.MainRepository
import com.eduardolima.statistics_covid19_br.data.repository.MainResults


//manter o viewModel isolado do framework do andorid
class MainViewModel(val mainRepository: MainRepository) : ViewModel() {
    val stateLiveData: MutableLiveData<List<State>> = MutableLiveData()
    val totalLiveData: MutableLiveData<Total> = MutableLiveData()

    fun getStates() {
        mainRepository.getStates { result: MainResults ->
            when (result) {
                is MainResults.SucessStates -> {
                    stateLiveData.value = result.states
                }

                is MainResults.ErrorStates -> {
                    Log.i("EROR", result.error)
                }

            }


        }
    }

    fun getTotal() {
        mainRepository.getTotal { result: MainResults ->
            when (result) {
                is MainResults.SucessTotal -> {
                    totalLiveData.value = result.total
                }

                is MainResults.ErrorTotal -> {
                    Log.i("EROR", result.error)
                }

            }


        }
    }


    class ViewModelFactory(val mainRepository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(mainRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }
}