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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.airlines.data.Customer
import com.example.airlines.network.CustomerApiService
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class SignupFragment : Fragment() {

    private  lateinit var fnameTxt: EditText
    private  lateinit var lnameTxt: EditText
    private  lateinit var nationality: Spinner
    private  lateinit var gender: RadioGroup
    private  lateinit var dateOfBirth: EditText
    private  lateinit var searchTxt: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.signup_fragment, container, false)

        fnameTxt = view.findViewById(R.id.fname_txt)
        lnameTxt = view.findViewById(R.id.lname_txt)
        nationality = view.findViewById(R.id.nationality_txt)
        gender = view.findViewById(R.id.gender_txt)
        dateOfBirth = view.findViewById(R.id.dob_txt)

        view.findViewById<View>(R.id.signup_button).setOnClickListener {
            val customer = readFields()
            Log.d("customer object:", " = > $customer")
            GlobalScope.launch(Dispatchers.IO) {
                val response: Response<Void> =
                    CustomerApiService.getInstance().
                        insertCustomerAsync(customer).await()
                Log.d("", response.message())
            }
            Toast.makeText(context, "Customer Added", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun readFields(): Customer {
        val name = fnameTxt.text.toString() + " " + lnameTxt.text.toString()
        return Customer(
            searchTxt.text.toString().toLong(),
            name,
            gender.focusedChild.toString(),
            nationality.selectedItem.toString(),
            dateOfBirth.text.toString()
        )
    }
}
