package com.eds.univo.prograiv.parcial3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eds.univo.prograiv.parcial3.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var spnBackground: Spinner
    private lateinit var spnButton: Spinner

    val colorArray = arrayOf("Negro", "Azul", "Verde")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        spnBackground.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorArray)
    }
}
