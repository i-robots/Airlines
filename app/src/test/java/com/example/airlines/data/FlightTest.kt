package com.example.airlines.data

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FlightTest{

    private lateinit var f: Flight

    @Before
    fun setUp() {
        f  = Flight(5, "Ethipia, AA", "Kenya, mombassa", "3:00", 5000)
    }

    @Test
    fun test_default_values() {
        Assert.assertTrue(f.flightPlanes.isEmpty())
    }

}