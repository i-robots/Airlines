package com.example.airlines.data

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.airlines.MainCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.matchers.Null
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@SmallTest
@Config(sdk = intArrayOf(Build.VERSION_CODES.O_MR1))
class FlightDaoTest {
    private lateinit var database: AppDatabase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertFlightkAndGetById() = runBlockingTest {
        // GIVEN - insert a task
        val flight = Flight(1,"xxx","addis","2",1234)
        database.flightDao().insertFlight(flight)

        // WHEN - Get the task by id from the database
        val loaded = database.flightDao().getFlightByNo(flight.flightNo)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Flight>(loaded as Flight, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.flightNo, CoreMatchers.`is`(flight.flightNo))
        MatcherAssert.assertThat(loaded.root, CoreMatchers.`is`(flight.root))
        MatcherAssert.assertThat(loaded.destination, CoreMatchers.`is`(flight.destination))
        MatcherAssert.assertThat(loaded.flightDuration, CoreMatchers.`is`(flight.flightDuration))
        MatcherAssert.assertThat(loaded.flightExpense, CoreMatchers.`is`(flight.flightExpense))
    }
    @Test
    fun insertFlightReplacesOnConflict() = runBlockingTest {
        // Given that a task is inserted
        val flight = Flight(1,"xxx","addis","2",1234)
        database.flightDao().insertFlight(flight)

        // When a task with the same id is inserted
        val newflight = Flight(flight.flightNo,"xxx2","addis2","3",1234)
        database.flightDao().insertFlight(newflight)

        // THEN - The loaded data contains the expected values
        val loaded = database.flightDao().getFlightByNo(newflight.flightNo)
        MatcherAssert.assertThat(loaded?.flightNo, CoreMatchers.`is`(flight.flightNo))
        MatcherAssert.assertThat(loaded?.root, CoreMatchers.`is`("xxx2"))
        MatcherAssert.assertThat(loaded?.destination, CoreMatchers.`is`("addis2"))
        MatcherAssert.assertThat(loaded?.flightDuration, CoreMatchers.`is`("3"))
        MatcherAssert.assertThat(loaded?.flightExpense, CoreMatchers.`is`(1234))

    }
    @Test
    fun deleteFlight() = runBlockingTest {
          // Given a task inserted
            val flight = Flight(1,"xxx","addis","2",1234)
            database.flightDao().insertFlight(flight)

            // When deleting a task by id
            database.flightDao().deleteFlight(flight)

            // THEN - The list is empty
            val fl = database.flightDao().getFlightByNo(flight.flightNo)
            assertEquals(fl,null)
        }
            @Test
        fun updateFlightAndGetByFlightNo() = runBlockingTest {
            // When inserting a task
                val flight1 =Flight(1,"xxx","addis","2",1234)
            database.flightDao().insertFlight(flight1)

            // When the task is updated
            val updatedFlight = Flight(1,"yyyx","gonder","4:00",1534)
            database.flightDao().updateFlight(updatedFlight)

            // THEN - The loaded data contains the expected values
            val loaded = database.flightDao().getFlightByNo(updatedFlight.flightNo)
            MatcherAssert.assertThat(loaded?.flightNo, CoreMatchers.`is`(flight1.flightNo))
            MatcherAssert.assertThat(loaded?.root, CoreMatchers.`is`("yyyx"))
            MatcherAssert.assertThat(loaded?.destination, CoreMatchers.`is`("gonder"))
            MatcherAssert.assertThat(loaded?.flightDuration, CoreMatchers.`is`("4:00"))
            MatcherAssert.assertThat(loaded?.flightExpense, CoreMatchers.`is`(1534))
            }



}