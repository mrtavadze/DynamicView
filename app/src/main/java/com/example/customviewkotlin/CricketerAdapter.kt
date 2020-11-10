package com.example.customviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class CricketerAdapter(cricketersList: ArrayList<Cricketer>) :
    RecyclerView.Adapter<CricketerAdapter.CricketerView?>() {
    var cricketersList = ArrayList<Cricketer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CricketerView {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_cricketer, parent, false)
        return CricketerView(view)
    }

    override fun onBindViewHolder(holder: CricketerView, position: Int) {
        val cricketer = cricketersList[position]
        holder.textCricketerName.setText(cricketer.cricketerName)
        holder.textTeamName.setText(cricketer.teamName)
    }

    override fun getItemCount(): Int {
        return cricketersList.size
    }


    inner class CricketerView(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textCricketerName: TextView
        var textTeamName: TextView

        init {
            textCricketerName =
                itemView.findViewById<View>(R.id.text_cricketer_name) as TextView
            textTeamName = itemView.findViewById<View>(R.id.text_team_name) as TextView
        }
    }

    init {
        this.cricketersList = cricketersList
    }
}
