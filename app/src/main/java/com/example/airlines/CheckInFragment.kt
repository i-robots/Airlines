package com.example.airlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.airlines.R

/**
 * Presents how multiple steps flow could be implemented.
 */
class CheckInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        // Use type-safe arguments
//        val safeArgs: FlowStepFragmentArgs by navArgs()
//        val flowStepNumber = safeArgs.flowStepNumber

        return inflater.inflate(R.layout.check_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.checkInButton).setOnClickListener {
            Toast.makeText(context, "checked In", Toast.LENGTH_LONG).show()
        }
    }
}
