package com.example.airlines


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.airlines.data.Plane
import com.example.airlines.network.PlaneApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class AddPlaneFragment : Fragment() {

    private  lateinit var name: EditText
    private  lateinit var numberOfSeats: EditText
    private  lateinit var quality: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_plane, container, false)

        name = view.findViewById(R.id.name_et)
        numberOfSeats = view.findViewById(R.id.noOfSeats_et)
        quality = view.findViewById(R.id.quality_et)

//        view.findViewById<View>(R.id.signup_button).setOnClickListener {
//            val plane = readFields()
//            Log.d("flight object:", " = > $plane")
//            //PlaneRepo.insertPlane(plane)
//            Toast.makeText(context, "Plane Added", Toast.LENGTH_LONG).show()
//        }

        return view

    }

    private fun readFields(): Plane {
        return Plane(1,
            name.text.toString(),
            numberOfSeats.text.toString().toInt(),
            quality.text.toString()
        )
    }


}
