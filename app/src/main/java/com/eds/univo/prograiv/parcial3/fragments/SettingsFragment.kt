package com.eds.univo.prograiv.parcial3.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter

import com.eds.univo.prograiv.parcial3.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment() {

    private lateinit var spnBackground: Spinner
    private lateinit var spnButton: Spinner

    val colorArray = arrayOf("Negro", "Azul", "Verde")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        // Inflate the layout for this fragment
        spnBackground = view.findViewById(R.id.spnBackground)
        spnButton = view.findViewById(R.id.spnButton)

        spnBackground.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorArray)

        return view
    }




}
