package com.example.airlines

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.airlines.data.Flight
import com.example.airlines.data.Plane
import com.example.airlines.databinding.FragmentAddFlightBinding
import com.example.airlines.viewmodel.FlightViewModel
import com.example.airlines.viewmodel.PlaneViewModel

class AddFlightFragment : Fragment() {

    private lateinit var binding: FragmentAddFlightBinding
    private lateinit var flightViewModel: FlightViewModel
    private lateinit var planeViewModel: PlaneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_flight, container, false)

        var list_of_items = arrayOf("SKYBUS", "BOEING", "AIRMAX")

        val planeAdapter = ArrayAdapter(binding.root.context,android.R.layout.simple_spinner_item,list_of_items)

        val countryAdapter = ArrayAdapter.createFromResource(binding.root.context,R.array.country,
                        android.R.layout.simple_spinner_item)

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerRoot.adapter = countryAdapter
        binding.spinnerDest.adapter = countryAdapter
        binding.spinner.adapter = planeAdapter


        flightViewModel = ViewModelProviders.of(this).get(FlightViewModel::class.java)

        binding.addFlightButton.setOnClickListener {
            val flight = readFields(binding.flightNo.text.toString().toInt(),
                binding.durationEt.text.toString(),
                binding.expenseEt.text.toString().toInt(),
                binding.spinnerRoot.selectedItem.toString(),
                binding.spinnerDest.selectedItem.toString(),
                binding.spinner.selectedItem.toString())
            flightViewModel.insertFlight(flight)
            Toast.makeText(context, "Flight Added", Toast.LENGTH_LONG).show()
        }

        binding.deleteButtonId.setOnClickListener {
            val flight = readFields(binding.flightNo.text.toString().toInt(),
                binding.durationEt.text.toString(),
                binding.expenseEt.text.toString().toInt(),
                binding.spinnerRoot.selectedItem.toString(),
                binding.spinnerDest.selectedItem.toString(),
                binding.spinner.selectedItem.toString())
                flightViewModel.deleteFlight(flight)
        }

        binding.updateButtonId.setOnClickListener {
            val flight = readFields(binding.flightNo.text.toString().toInt(),
                binding.durationEt.text.toString(),
                binding.expenseEt.text.toString().toInt(),
                binding.spinnerRoot.selectedItem.toString(),
                binding.spinnerDest.selectedItem.toString(),
                binding.spinner.selectedItem.toString())
            flightViewModel.updateFlight(flight)
        }

        binding.findButtonId.setOnClickListener {
            val id = binding.findTextview.text.toString().toInt()
                flightViewModel.getFlightById(id).observe(this, Observer {
                    flight->flight?.let {
                        binding.viewmodel = flight
                    }
                })
        }

        return binding.root
    }

    private fun readFields(flightNo:Int, duration: String, expense:Int, root: String, dest:String,plane:String): Flight {
        var plane = Plane(4,plane,101,"standard")
        val flight = Flight(flightNo,root,dest, duration, expense, plane)
        Log.d("assert","${flight}")
        return flight
    }

}
