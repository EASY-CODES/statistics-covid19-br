package com.eduardolima.statistics_covid19_br.data.repository

import com.eduardolima.statistics_covid19_br.data.model.State
import com.eduardolima.statistics_covid19_br.data.model.Total

sealed class MainResults {

    class SucessStates(val states: List<State>) : MainResults()
    class ErrorStates(val error: String) : MainResults()



    class SucessTotal(val total: Total) : MainResults()
    class ErrorTotal(val error: String) : MainResults()
}