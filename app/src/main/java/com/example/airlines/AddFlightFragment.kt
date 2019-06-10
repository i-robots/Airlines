package com.example.airlines


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.airlines.data.Flight
import com.example.airlines.data.Plane
import com.example.airlines.network.FlightApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class AddFlightFragment : Fragment() {

    private  lateinit var rootSpinner: Spinner
    private  lateinit var destSpinner: Spinner
    private  lateinit var duration: EditText
    private  lateinit var Expense: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_flight, container, false)

        val spinnerRootCountry = view.findViewById<Spinner>(R.id.spinnerRoot)
        val spinnerDestCountry = view.findViewById<Spinner>(R.id.spinnerDest)

        val adapter2 = ArrayAdapter.createFromResource(
            view.context,R.array.country,
            android.R.layout.simple_spinner_item)
        val adapter1 = ArrayAdapter.createFromResource(
            view.context,R.array.country,
            android.R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRootCountry.adapter = adapter2
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDestCountry.adapter = adapter1

        rootSpinner = view.findViewById(R.id.spinnerRoot)
        destSpinner = view.findViewById(R.id.spinnerDest)
        duration = view.findViewById(R.id.duration_Et)
        Expense = view.findViewById(R.id.expense_et)

        view.findViewById<View>(R.id.signup_button).setOnClickListener {
            val flight = readFields()
            Log.d("flight object:", " = > $flight")
            GlobalScope.launch(Dispatchers.IO) {
                val response: Response<Void> =
                    FlightApiService.getInstance().
                        insertFlightAsync(flight).await()
                Log.d("", response.message())
            }
            Toast.makeText(context, "Flight Added", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun readFields(): Flight {
        var plane: Plane = Plane(1,"Boing",101,"standard")
        return Flight(1,
            rootSpinner.selectedItem.toString(),
            destSpinner.selectedItem.toString(),
            duration.text.toString(),
            Expense.text.toString().toInt(),
            plane
        )
    }

}
