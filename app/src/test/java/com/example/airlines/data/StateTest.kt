package com.example.airlines.data

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class StateTest{
    private lateinit var state: State

    @Before
    fun setUp() {
        state = State(1, "Ethiopia", Continent("Africa"))
    }

    @Test
    fun test_values() {
        val c = Continent("Africa")
        Assert.assertEquals(1, state.stateId)
        Assert.assertEquals("Ethiopia", state.stateName)
        Assert.assertEquals(c, state.continent)
    }
}