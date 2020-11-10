package com.example.customviewkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ActivityCricketers : AppCompatActivity() {
    var cricketersList: ArrayList<Cricketer>? = ArrayList<Cricketer>()
    override fun onCreate(savedInstanceState: Bundle?) {
        var recyclerCricketers: RecyclerView? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cricketers)
        recyclerCricketers = findViewById(R.id.recycler_cricketers)
        val layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerCricketers.setLayoutManager(layoutManager)
        cricketersList =
            intent.extras!!.getSerializable("list") as ArrayList<Cricketer>?
        recyclerCricketers.setAdapter(CricketerAdapter(cricketersList as ArrayList<Cricketer>))
    }
}