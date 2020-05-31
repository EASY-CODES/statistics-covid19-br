package com.eduardolima.statistics_covid19_br.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduardolima.statistics_covid19_br.R
import com.eduardolima.statistics_covid19_br.model.State
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_state.view.*


class AdapterState(
    private val states: List<State>,
    private val onItemClickListener: ((state: State) -> Unit)
) :
    RecyclerView.Adapter<AdapterState.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return MyViewHolder(
            view, onItemClickListener
        )

    }

    override fun getItemCount() = states.count();

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(states[position])
    }

    class MyViewHolder(itemView: View, private val onItemClickListener: ((state: State) -> Unit)) :
        RecyclerView.ViewHolder(itemView) {

        val image = itemView.itemimage
        val state = itemView.itemname
        val cases = itemView.item_cases
        val deaths = itemView.item_deaths
        val suspects = itemView.item_suspects
        val refuses = itemView.item_refuses

        fun bindView(ostate: State) {

            Picasso.get()
                .load("https://devarthurribeiro.github.io/covid19-brazil-api/static/flags/" + ostate.uf + ".png")
                .into(image);

            state.text = ostate.state
            cases.text = ostate.cases.toString()
            deaths.text = ostate.deaths.toString()
            suspects.text = ostate.suspects.toString()
            refuses.text = ostate.refuses.toString()

            //onclickitem
            itemView.setOnClickListener {
                onItemClickListener(ostate)
            }

        }
    }


}


