package com.stareme.ocbcsimple

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.stareme.ocbcsimple.ui.login.LoginActivity
import com.stareme.ocbcsimple.ui.main.MainActivity
import kotlinx.coroutines.delay
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TasksActivityTest {
    @Test
    fun createOneTask_deleteTask() {

        // start up Tasks screen
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Add active task
        onView(withId(R.id.makeTransfer)).perform(click())
        activityScenario.close()
    }

    @Test
    fun createOneLoginTask() {

        // start up Tasks screen
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)

        // Add active task
        onView(withId(R.id.username)).perform(replaceText("ocbc"))
        onView(withId(R.id.password)).perform(replaceText("123456"))
        onView(withId(R.id.login)).perform(click())
        activityScenario.close()
    }
}