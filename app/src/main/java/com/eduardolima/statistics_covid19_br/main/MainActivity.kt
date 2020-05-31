package com.eduardolima.statistics_covid19_br.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardolima.statistics_covid19_br.R
import com.eduardolima.statistics_covid19_br.data.repository.MainRepositoryApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(maintoolbar)


        //uso do view model com injeção de dependencias
        var mainViewModel: MainViewModel = MainViewModel.ViewModelFactory(MainRepositoryApi()).create(MainViewModel::class.java)

        mainViewModel.stateLiveData.observe(this, Observer {
            it?.let { states ->
                with(mainrecycler) {
                    layoutManager =
                        LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    adapter = AdapterState(states) { state ->

                    }
                }
            }
        })


        mainViewModel.totalLiveData.observe(this, Observer {
            it?.let { t ->
                maintoolbar.subtitle = "last update: " + t.last_update
                main_cases.text = t.recovered.toString()
                main_confirmed.text = t.confirmed.toString()
                main_deaths.text = t.deaths.toString()
                main_recovered.text = t.recovered.toString()
            }
        })





        mainViewModel.getTotal()
        mainViewModel.getStates()
    }


}
