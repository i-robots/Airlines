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

        val countryAdapter = ArrayAdapter.createFromResource(binding.root.context,R.array.country,
                        android.R.layout.simple_spinner_item)

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRoot.adapter = countryAdapter
        binding.spinnerDest.adapter = countryAdapter


        flightViewModel = ViewModelProviders.of(this).get(FlightViewModel::class.java)

        binding.addFlightButton.setOnClickListener {
            val flight = readFields(binding.flightNo.text.toString().toInt(),
                binding.durationEt.text.toString(),
                binding.expenseEt.text.toString().toInt(),
                binding.spinnerRoot.selectedItem.toString(),
                binding.spinnerDest.selectedItem.toString())
            flightViewModel.insertFlight(flight)
            Toast.makeText(context, "Flight Added", Toast.LENGTH_LONG).show()
        }

        binding.deleteButtonId.setOnClickListener {

        }

        binding.updateButtonId.setOnClickListener {
            val id = binding.findTextview.text.toString().toInt()

        }

        binding.findButtonId.setOnClickListener {
            val id = binding.findTextview.text.toString().toInt()
            flightViewModel.getFlightById(id)
//            binding.flightNo.setText(flight.flightNo)
//            binding.durationEt.setText(flight.flightDuration)
//            binding.expenseEt.setText(flight.flightExpense)
//            binding.spinnerRoot.setSelection(countryAdapter.getPosition(flight.root))
//            binding.spinnerDest.setSelection(countryAdapter.getPosition(flight.dest))

        }

        return binding.root
    }

    private fun readFields(flightNo:Int, duration: String, expense:Int, root: String, dest:String): Flight {
        var plane = Plane(1,"Boing",101,"standard")
        return Flight(flightNo,root,dest, duration, expense, plane)
    }

}
