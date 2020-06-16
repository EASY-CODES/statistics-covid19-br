package com.eduardolima.statistics_covid19_br.connection

import retrofit2.Call
import retrofit2.http.GET

interface APICovidBr {

    @GET("api/report/v1")
    fun listStates(): Call<String>

    @GET("api/report/v1/brazil")
    fun totalBr(): Call<String>

}
