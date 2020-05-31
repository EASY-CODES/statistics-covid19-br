package com.eduardolima.statistics_covid19_br.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduardolima.statistics_covid19_br.connection.APIService
import com.eduardolima.statistics_covid19_br.model.State
import com.eduardolima.statistics_covid19_br.model.Total
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//manter o viewModel isolado do framework do andorid
class ViewModelMain : ViewModel() {
    val stateLiveData: MutableLiveData<List<State>> = MutableLiveData()
    val totalLiveData: MutableLiveData<Total> = MutableLiveData()

    fun getStates() {
        APIService.service.listStates().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

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


                    stateLiveData.value = statesList

                }
            }

        })
    }

    fun getTotal() {
        APIService.service.totalBr().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {

                    val data = JSONObject(response.body())
                    val total = data.getJSONObject("data")

                    totalLiveData.value = Total(
                        total.getInt("cases"),
                        total.getInt("confirmed"),
                        total.getInt("deaths"),
                        total.getInt("recovered"),
                        total.getString("updated_at")
                    )

                }


            }

        })
    }
}