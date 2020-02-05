package com.example.homework.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.homework.R

class SettingsFragment: Fragment() {

    private lateinit var genderMaleBtn: ToggleButton
    private lateinit var genderFemaleBtn: ToggleButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        genderFemaleBtn = view.findViewById(R.id.btn_toggle_female)
        genderMaleBtn = view.findViewById(R.id.btn_toggle_male)

        genderMaleBtn.setOnCheckedChangeListener { buttonView, isChecked -> if (isChecked) {
            genderFemaleBtn.isChecked = false
        } }
        genderFemaleBtn.setOnCheckedChangeListener { buttonView, isChecked -> if (isChecked) genderMaleBtn.isChecked = false }

        genderMaleBtn.setOnClickListener {}
        genderFemaleBtn.setOnClickListener {}

        return view
    }
}