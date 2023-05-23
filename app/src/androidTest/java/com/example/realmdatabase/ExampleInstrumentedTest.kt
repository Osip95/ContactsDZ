package com.example.realmdatabase


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToLastPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.realmdatabase.mainscreen.ContactsAdapter
import com.example.realmdatabase.mainscreen.MainActivity
import org.hamcrest.Matcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun addContactCheck() {

        onView(withId(R.id.fabAddContact))
            .check(matches(isDisplayed()))
            .perform(click())

        val name = "Nikita"

        onView(withId(R.id.etName))
            .check(matches(isDisplayed()))
            .perform(typeText(name))
            .check(matches(withText(name)))

        val surname = "Panchenko"

        onView(withId(R.id.etSurname))
            .check(matches(isDisplayed()))
            .perform(typeText(surname))
            .check(matches(withText(surname)))

        val phone = "+799999990"

        onView(withId(R.id.etNumber))
            .check(matches(isDisplayed()))
            .perform(typeText(phone))
            .check(matches(withText(phone)))

        onView(withId(R.id.btnSave))
            .check(matches(isDisplayed()))
            .perform(click())

        pressBack()
        pressBack()

        fun clickItemWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<View>(id) as View
                    v.performClick()
                }
            }
        }

        var lastItemPositionRv = 0
        activityScenarioRule.scenario.onActivity { activity ->
            lastItemPositionRv =
                activity.findViewById<RecyclerView>(R.id.rvContacts).adapter!!.itemCount - 1
        }

        onView(withId(R.id.rvContacts))
            .perform(
                actionOnItemAtPosition<ContactsAdapter.MyViewHolder>(
                    lastItemPositionRv,
                    clickItemWithId(R.id.imageView)
                )
            )

        val newName = "Alex"

        onView(withId(R.id.changeEtName))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText(newName))

    }

    @Test
    fun contactChangeCheck() {

        fun clickItemWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<View>(id) as View
                    v.performClick()
                }
            }
        }

        var lastItemPositionRv = 0
        activityScenarioRule.scenario.onActivity { activity ->
            lastItemPositionRv =
                activity.findViewById<RecyclerView>(R.id.rvContacts).adapter!!.itemCount - 1
        }

        onView(withId(R.id.rvContacts))
            .perform(
                actionOnItemAtPosition<ContactsAdapter.MyViewHolder>(
                    lastItemPositionRv,
                    clickItemWithId(R.id.imageView)
                )
            )

        val newName = "Alex"

        onView(withId(R.id.changeEtName))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText(newName))

        val newSurname = "Shrerder"

        onView(withId(R.id.changeEtSurname))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText(newSurname))

        val newNumber = "9990245790"

        onView(withId(R.id.changeEtNumber))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText(newNumber))

        onView(withId(R.id.btnSaveChange))
            .check(matches(isDisplayed()))
            .perform(click())

        pressBack()
        pressBack()

        onView(withId(R.id.rvContacts))
            .check(matches(isDisplayed()))
            .perform(
                scrollToLastPosition<ContactsAdapter.MyViewHolder>()
            )



    }
}