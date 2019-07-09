package com.example.airlines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.airlines.viewmodel.FlightViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airlines.databinding.FragmentSeeFlightBinding

class SeeFlightFragment : Fragment() {
    private lateinit var flightViewModel: FlightViewModel
    private lateinit var binding: FragmentSeeFlightBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_see_flight, container, false)
        val adapter = FlightListAdapter(this.requireContext())
        binding.flightListRecyclerId.layoutManager = LinearLayoutManager(this.requireContext())
        binding.flightListRecyclerId.adapter = adapter
        flightViewModel = ViewModelProviders.of(this).get(FlightViewModel::class.java)
        flightViewModel.allFlight.observe(this, Observer {
            flights->flights?.let {
            adapter.setFlight(flights)
        }
        })
        return binding.root
    }

}
