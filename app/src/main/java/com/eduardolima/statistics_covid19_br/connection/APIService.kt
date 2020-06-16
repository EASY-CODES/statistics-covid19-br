package com.eduardolima.statistics_covid19_br.connection

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object APIService {
    private fun init(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://covid19-brazil-api.now.sh/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    val service: APICovidBr = init().create(APICovidBr::class.java)
}