package com.a20190529_sureshkumarkumaravel_nycschools

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.a20190529_sureshkumarkumaravel_nycschools.EspressoExtra.childAtPosition
import com.a20190529_sureshkumarkumaravel_nycschools.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.allOf

/**
 * Created on 2019-05-30.
 *
 * @author kumars
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun waitForResultAndTap() {

        // TODO: this should be handled correctly by "IdlingResource"
        Thread.sleep(3000)

        val storeItemView = onView(
                allOf(childAtPosition(withId(R.id.recycler_view), 1),
                        isDisplayed()))
        storeItemView.perform(click())

        // TODO: test all other use cases

        // TODO: We can mock the API to avoid network calls
    }
}