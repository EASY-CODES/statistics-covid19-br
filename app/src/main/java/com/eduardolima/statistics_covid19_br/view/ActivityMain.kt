package com.eduardolima.statistics_covid19_br.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardolima.statistics_covid19_br.R
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(maintoolbar)


        var viewModelMain: ViewModelMain = of(this).get(ViewModelMain::class.java)

        viewModelMain.stateLiveData.observe(this, Observer {
            it?.let { states ->
                with(mainrecycler) {
                    layoutManager =
                        LinearLayoutManager(this@ActivityMain, RecyclerView.VERTICAL, false)
                    adapter = AdapterState(states) { state ->

                    }
                }
            }
        })


        viewModelMain.totalLiveData.observe(this, Observer {
            it?.let { t ->
                maintoolbar.subtitle = "last update: " + t.last_update
                main_cases.text = t.recovered.toString()
                main_confirmed.text = t.confirmed.toString()
                main_deaths.text = t.deaths.toString()
                main_recovered.text = t.recovered.toString()
            }
        })




        viewModelMain.getTotal()
        viewModelMain.getStates()
    }


}
