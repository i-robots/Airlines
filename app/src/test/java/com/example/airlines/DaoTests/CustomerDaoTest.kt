package com.example.airlines.data

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.airlines.MainCoroutineRule
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.apache.tools.ant.Task
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
open class CustomerDaoTest {
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
        fun insertCustomerkAndGetById() = runBlockingTest {
            // GIVEN - insert a task
            val customer = Customer(11,"name1","F","Eth","1/1/2011")
            database.customerDao().insertCustomer(customer)

            // WHEN - Get the task by id from the database
            val loaded = database.customerDao().getCustomerById(customer.id)

            // THEN - The loaded data contains the expected values
            MatcherAssert.assertThat<Customer>(loaded as Customer, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(customer.id))
            MatcherAssert.assertThat(loaded.name, CoreMatchers.`is`(customer.name))
            MatcherAssert.assertThat(loaded.gender, CoreMatchers.`is`(customer.gender))
            MatcherAssert.assertThat(loaded.nationality, CoreMatchers.`is`(customer.nationality))
            MatcherAssert.assertThat(loaded.dob, CoreMatchers.`is`(customer.dob))
        }

        @Test
        fun insertCustomerReplacesOnConflict() = runBlockingTest {
            // Given that a task is inserted
            val customer = Customer(11,"name1","F","Eth","1/1/2011")
            database.customerDao().insertCustomer(customer)

            // When a task with the same id is inserted
            val newcustomer = Customer(customer.id, "name","M","American","1/2/2011")
            database.customerDao().insertCustomer(newcustomer)

            // THEN - The loaded data contains the expected values
            val loaded = database.customerDao().getCustomerById(newcustomer.id)
            MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(customer.id))
            MatcherAssert.assertThat(loaded?.name, CoreMatchers.`is`("name"))
            MatcherAssert.assertThat(loaded?.gender, CoreMatchers.`is`("M"))
            MatcherAssert.assertThat(loaded?.nationality, CoreMatchers.`is`("American"))
            MatcherAssert.assertThat(loaded?.dob, CoreMatchers.`is`("1/2/2011"))

        }
    @Test
    fun deleteCustomer() = runBlockingTest {
        // Given a task inserted
        val newcustomer = Customer(1, "name","M","American","1/2/2011")
        database.customerDao().insertCustomer(newcustomer)

        // When deleting a task by id
        database.customerDao().deleteCustomer(newcustomer)

        // THEN - The list is empty
        val fl = database.customerDao().getCustomerById(newcustomer.id)
       assertEquals(fl, null)
    }


//        @Test
//        fun insertTaskAndGetTasks() = runBlockingTest {
//            // GIVEN - insert a task
//            val task = Task("title", "description")
//            database.taskDao().insertTask(task)
//
//            // WHEN - Get tasks from the database
//            val tasks = database.taskDao().getTasks()
//
//            // THEN - There is only 1 task in the database, and contains the expected values
//            MatcherAssert.assertThat(tasks.size, CoreMatchers.`is`(1))
//            MatcherAssert.assertThat(tasks[0].id, CoreMatchers.`is`(task.id))
//            MatcherAssert.assertThat(tasks[0].title, CoreMatchers.`is`(task.title))
//            MatcherAssert.assertThat(tasks[0].description, CoreMatchers.`is`(task.description))
//            MatcherAssert.assertThat(tasks[0].isCompleted, CoreMatchers.`is`(task.isCompleted))
//        }
//
//        @Test
//        fun updateTaskAndGetById() = runBlockingTest {
//            // When inserting a task
//            val originalTask = Task("title", "description")
//            database.taskDao().insertTask(originalTask)
//
//            // When the task is updated
//            val updatedTask = Task("new title", "new description", true, originalTask.id)
//            database.taskDao().updateTask(updatedTask)
//
//            // THEN - The loaded data contains the expected values
//            val loaded = database.taskDao().getTaskById(originalTask.id)
//            MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(originalTask.id))
//            MatcherAssert.assertThat(loaded?.title, CoreMatchers.`is`("new title"))
//            MatcherAssert.assertThat(loaded?.description, CoreMatchers.`is`("new description"))
//            MatcherAssert.assertThat(loaded?.isCompleted, CoreMatchers.`is`(true))
//        }
//
//        @Test
//        fun updateCompletedAndGetById() = runBlockingTest {
//            // When inserting a task
//            val task = Task("title", "description", true)
//            database.taskDao().insertTask(task)
//
//            // When the task is updated
//            database.taskDao().updateCompleted(task.id, false)
//
//            // THEN - The loaded data contains the expected values
//            val loaded = database.taskDao().getTaskById(task.id)
//            MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(task.id))
//            MatcherAssert.assertThat(loaded?.title, CoreMatchers.`is`(task.title))
//            MatcherAssert.assertThat(loaded?.description, CoreMatchers.`is`(task.description))
//            MatcherAssert.assertThat(loaded?.isCompleted, CoreMatchers.`is`(false))
//        }
//
//        @Test
//        fun deleteTaskByIdAndGettingTasks() = runBlockingTest {
//            // Given a task inserted
//            val task = Task("title", "description")
//            database.taskDao().insertTask(task)
//
//            // When deleting a task by id
//            database.taskDao().deleteTaskById(task.id)
//
//            // THEN - The list is empty
//            val tasks = database.taskDao().getTasks()
//            MatcherAssert.assertThat(tasks.isEmpty(), CoreMatchers.`is`(true))
//        }
//
//        @Test
//        fun deleteTasksAndGettingTasks() = runBlockingTest {
//            // Given a task inserted
//            database.taskDao().insertTask(Task("title", "description"))
//
//            // When deleting all tasks
//            database.taskDao().deleteTasks()
//
//            // THEN - The list is empty
//            val tasks = database.taskDao().getTasks()
//            MatcherAssert.assertThat(tasks.isEmpty(), CoreMatchers.`is`(true))
//        }
//
//        @Test
//        fun deleteCompletedTasksAndGettingTasks() = runBlockingTest {
//            // Given a completed task inserted
//            database.taskDao().insertTask(Task("completed", "task", true))
//
//            // When deleting completed tasks
//            database.taskDao().deleteCompletedTasks()
//
//            // THEN - The list is empty
//            val tasks = database.taskDao().getTasks()
//            MatcherAssert.assertThat(tasks.isEmpty(), CoreMatchers.`is`(true))
//        }
    }