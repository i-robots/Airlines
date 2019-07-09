package com.example.airlines.data

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CustomerTest{
    private lateinit var customer: Customer

    @Before
    fun setUp() {
        customer = Customer(1, "Tumsa umeta", "Male", "ETH","01-08-2001")
    }

    @Test
    fun test_values() {
        Assert.assertEquals(1, customer.id)
        Assert.assertEquals("Tumsa umeta", customer.name)
        Assert.assertEquals("Male", customer.gender)
    }
}