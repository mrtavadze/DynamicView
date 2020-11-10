package com.example.customviewkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var cricketersList = ArrayList<Cricketer>()
    var layoutList: LinearLayout? = null
    var teamList: MutableList<String?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {

        var buttonAdd: Button? = null
        var buttonSubmitList: Button? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutList = findViewById(R.id.layout_list)
        buttonAdd = findViewById(R.id.button_add)
        buttonSubmitList = findViewById(R.id.button_submit_list)
        buttonAdd.setOnClickListener(this)
        buttonSubmitList.setOnClickListener(this)
        teamList.add("Team")
        teamList.add("India")
        teamList.add("Australia")
        teamList.add("England")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_add -> addView()
            R.id.button_submit_list -> if (checkIfValidAndRead()) {
                val intent = Intent(this@MainActivity, ActivityCricketers::class.java)
                val bundle = Bundle()
                bundle.putSerializable("list", cricketersList)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    private fun checkIfValidAndRead(): Boolean {
        cricketersList.clear()
        var result = true
        for (i in 0 until layoutList!!.childCount) {
            val cricketerView = layoutList!!.getChildAt(i)
            val editTextName = cricketerView.findViewById<View>(R.id.edit_cricketer_name) as EditText
            val spinnerTeam = cricketerView.findViewById<View>(R.id.spinner_team) as AppCompatSpinner
            val cricketer = Cricketer()
            if (editTextName.text.toString() != "") {
                cricketer.cricketerName = (editTextName.text.toString())
            } else {
                result = false
                break
            }
            if (spinnerTeam.selectedItemPosition != 0) {
                cricketer.teamName = (teamList[spinnerTeam.selectedItemPosition])
            } else {
                result = false
                break
            }
            cricketersList.add(cricketer)
        }
        if (cricketersList.size == 0) {
            result = false
            Toast.makeText(this, "Add Cricketers First!", Toast.LENGTH_SHORT).show()
        } else if (!result) {
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show()
        }
        return result
    }

    private fun addView() {
        val cricketerView: View = layoutInflater.inflate(R.layout.row_add_cricketer, null, false)
        val editText = cricketerView.findViewById<View>(R.id.edit_cricketer_name) as EditText
        val spinnerTeam = cricketerView.findViewById<View>(R.id.spinner_team) as AppCompatSpinner
        val imageClose = cricketerView.findViewById<View>(R.id.image_remove) as ImageView
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, teamList as List<String>)
        spinnerTeam.adapter = arrayAdapter
        imageClose.setOnClickListener { removeView(cricketerView) }
        layoutList!!.addView(cricketerView)
    }

    private fun removeView(view: View) {
        layoutList!!.removeView(view)
    }
}
