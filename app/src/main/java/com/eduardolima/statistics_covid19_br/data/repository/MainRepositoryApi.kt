package com.eduardolima.statistics_covid19_br.data.repository

import com.eduardolima.statistics_covid19_br.data.api.APIService
import com.eduardolima.statistics_covid19_br.data.model.State
import com.eduardolima.statistics_covid19_br.data.model.Total
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepositoryApi : MainRepository {

    override fun getStates(statesResultCallback: (result: MainResults) -> Unit) {

        APIService.service.listStates().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                statesResultCallback(MainResults.ErrorStates(t.message.toString()))
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val statesList: MutableList<State> = mutableListOf()

                    val data = JSONObject(response.body())
                    val states = data.getJSONArray("data")
                    for (x in 0 until states.length() step 1) {
                        val state = states.getJSONObject(x)
                        statesList.add(
                            State(
                                state.getString("uf"),
                                state.getString("state"),
                                state.getInt("cases"),
                                state.getInt("deaths"),
                                state.getInt("suspects"),
                                state.getInt("refuses")
                            )
                        )
                    }
                    statesResultCallback(MainResults.SucessStates(statesList))
                } else {
                    statesResultCallback(MainResults.ErrorStates(response.message()))
                }
            }

        })

    }

    override fun getTotal(totalResultCallback: (result: MainResults) -> Unit) {
        APIService.service.totalBr().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                totalResultCallback(MainResults.ErrorTotal(t.message.toString()))
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {

                    val data = JSONObject(response.body())
                    val total = data.getJSONObject("data")

                    totalResultCallback(
                        MainResults.SucessTotal(
                            Total(
                                total.getInt("cases"),
                                total.getInt("confirmed"),
                                total.getInt("deaths"),
                                total.getInt("recovered"),
                                total.getString("updated_at")
                            )
                        )
                    )

                } else {
                    totalResultCallback(MainResults.ErrorTotal(response.message()))
                }


            }

        })
    }
}
