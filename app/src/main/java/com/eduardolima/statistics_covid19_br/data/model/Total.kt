package com.eduardolima.statistics_covid19_br.data.model

data class Total(
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val last_update: String
) {
}