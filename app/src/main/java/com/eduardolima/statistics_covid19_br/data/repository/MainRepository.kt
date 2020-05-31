package com.eduardolima.statistics_covid19_br.data.repository


interface MainRepository {
    fun getStates(statesResultCallback: (result: MainResults) -> Unit)
    fun getTotal(totalResultCallback: (result: MainResults) -> Unit)
}