package com.example.airlines.data

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.airlines.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@SmallTest
@Config(sdk = intArrayOf(Build.VERSION_CODES.O_MR1))
class PlaneDaoTest {
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
    fun insertPlaneAndGetById() = runBlockingTest {
        // GIVEN - insert a task
        val plane = Plane(1,"yyy",345,"high")
        database.planeDao().insertPlane(plane)

        // WHEN - Get the task by id from the database
        val loaded = database.planeDao().getPlaneByNo(plane.planeNo)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Plane>(loaded as Plane, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.planeNo, CoreMatchers.`is`(plane.planeNo))
        MatcherAssert.assertThat(loaded.name, CoreMatchers.`is`(plane.name))
        MatcherAssert.assertThat(loaded.noOfSeats, CoreMatchers.`is`(plane.noOfSeats))
        MatcherAssert.assertThat(loaded.quality, CoreMatchers.`is`(plane.quality))
    }

}