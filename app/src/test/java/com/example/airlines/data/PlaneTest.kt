package com.example.airlines.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PlaneTest{
    private lateinit var plane: Plane

    @Before fun setUp() {
        plane = Plane(1, "BOING 373", 120, "TOP")
    }

    @Test fun test_values() {
        assertEquals(1, plane.planeNo)
        assertEquals(120,plane.noOfSeats)
        assertEquals("TOP", plane.quality)
    }

    @Test fun test_toString() {
        assertEquals("BOING 373", plane.toString())
    }
}