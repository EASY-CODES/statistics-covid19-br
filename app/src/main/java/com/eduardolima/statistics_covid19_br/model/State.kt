package com.eduardolima.statistics_covid19_br.model

data class State(
    val uf: String,
    val state: String,
    val cases: Int,
    val deaths: Int,
    val suspects: Int,
    val refuses: Int
) {
}