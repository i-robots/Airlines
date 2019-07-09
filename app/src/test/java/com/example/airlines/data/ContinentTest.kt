package com.example.airlines.data

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ContinentTest {
    private lateinit var continent: Continent

    @Before
    fun setUp() {
        continent = Continent("Africa")
    }

    @Test
    fun test_values() {
        val c = Continent("Africa")
        Assert.assertEquals(c, continent.contientName)
    }
}