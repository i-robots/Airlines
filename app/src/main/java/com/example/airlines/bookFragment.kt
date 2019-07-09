/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.airlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment


class bookFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.book_fragment, container, false)


        val spinnersourceCountry = view.findViewById<Spinner>(R.id.spinnerSourceCountry)
        val spinnerDestCountry = view.findViewById<Spinner>(R.id.spinnerDestCountry)



        val adapter2 = ArrayAdapter.createFromResource(
            view.context,R.array.country,
            android.R.layout.simple_spinner_item)
        val adapter1 = ArrayAdapter.createFromResource(
            view.context,R.array.country,
            android.R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnersourceCountry.adapter = adapter2
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDestCountry.adapter = adapter1

        //val numberOfPassangers = view.findViewById<EditText>(R.id.numberofpassengers)

        val bookFLight = view.findViewById<Button>(R.id.book_Flight_Button)
        bookFLight.setOnClickListener{

        }

        return view
    }

}
