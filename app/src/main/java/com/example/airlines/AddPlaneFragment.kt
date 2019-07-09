package com.example.airlines


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.airlines.data.Plane
import com.example.airlines.databinding.FragmentAddFlightBinding
import com.example.airlines.databinding.FragmentAddPlaneBinding
import com.example.airlines.network.PlaneApiService
import com.example.airlines.viewmodel.PlaneViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class AddPlaneFragment : Fragment() {

    private lateinit var binding: FragmentAddPlaneBinding
    private lateinit var planeViewModel: PlaneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_plane, container, false)

        val qualityAdapter = ArrayAdapter.createFromResource(binding.root.context,R.array.quality,
            android.R.layout.simple_spinner_item)
        qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.qualitySpinner.adapter = qualityAdapter

        planeViewModel = ViewModelProviders.of(this).get(PlaneViewModel::class.java)

        binding.addPlaneButton.setOnClickListener {
            val plane = readFields(binding.planeNoEt.text.toString().toInt(),
                binding.nameEt.text.toString(),
                binding.noOfSeatsEt.text.toString().toInt(),
                binding.qualitySpinner.selectedItem.toString()
            )
            planeViewModel.insertPlane(plane)
            Toast.makeText(context, "Plane Added", Toast.LENGTH_LONG).show()
        }

        return binding.root

    }

    private fun readFields(planeNo:Int,name:String,noOfSeats:Int,quality:String): Plane {
        return Plane(planeNo,name,noOfSeats,quality)
    }


}
