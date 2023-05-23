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
import com.example.realmdatabase.CustomAction.Companion.clickItemWithId
import com.example.realmdatabase.mainscreen.ContactsAdapter
import com.example.realmdatabase.mainscreen.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers
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
    fun checkEmptyContactsScreen() {
        onView(withId(R.id.fabAddContact))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvContacts))
            .check(matches(isDisplayed()))
            .check(matches(Matchers.not(hasDescendant(Matchers.any(View::class.java)))))
    }

    @Test
    fun checkAddContactScreen() {
        onView(withId(R.id.fabAddContact))
            .perform(click())

        onView(withId(R.id.etName))
            .check(matches(isDisplayed()))
            .check(matches(CustomMatchers.withHint("Имя")))

        onView(withId(R.id.etSurname))
            .check(matches(isDisplayed()))
            .check(matches(CustomMatchers.withHint("Фамилия")))

        onView(withId(R.id.etNumber))
            .check(matches(isDisplayed()))
            .check(matches(CustomMatchers.withHint("Номер телефона")))

        onView(withId(R.id.btnSave))
            .check(matches(isDisplayed()))
            .check(matches(withText("Сохранить контакт")))
    }

    @Test
    fun checkAddingContact() {
        onView(withId(R.id.fabAddContact))
            .perform(click())

        onView(withId(R.id.etName))
            .perform(typeText("Nikita"))

        onView(withId(R.id.etSurname))
            .perform(typeText("Panchenko"))

        onView(withId(R.id.etNumber))
            .perform(typeText("+799999990"))

        onView(withId(R.id.btnSave))
            .perform(click())

        pressBack()
        pressBack()

        onView(withId(R.id.rvContacts))
            .check(matches(
                CustomMatchers.atPosition(
                    0,
                    hasDescendant(withText("Nikita Panchenko"))
                )
            ))
            .check(matches(CustomMatchers.atPosition(0, hasDescendant(withText("+799999990")))))
    }

@Test
fun checkChangeContactScreen() {
    checkAddingContact()
    onView(withId(R.id.rvContacts))
        .perform(
            actionOnItemAtPosition<ContactsAdapter.MyViewHolder>(
                0,
                clickItemWithId(R.id.imageView)
            )
        )


    onView(withId(R.id.changeEtName))
        .check(matches(isDisplayed()))
        .check(matches(withText("Nikita")))

    onView(withId(R.id.changeEtSurname))
        .check(matches(isDisplayed()))
        .check(matches(withText("Panchenko")))

    onView(withId(R.id.changeEtNumber))
        .check(matches(isDisplayed()))
        .check(matches(withText("+799999990")))

    onView(withId(R.id.btnSaveChange))
        .check(matches(isDisplayed()))

}

    @Test
    fun checkContactChange() {

        checkChangeContactScreen()

        onView(withId(R.id.changeEtName))
            .perform(click())
            .perform(clearText())
            .perform(typeText("Alex"))

        onView(withId(R.id.changeEtSurname))
            .perform(click())
            .perform(clearText())
            .perform(typeText("Shrerder"))

        onView(withId(R.id.changeEtNumber))
            .perform(click())
            .perform(clearText())
            .perform(typeText("9990245790"))

        onView(withId(R.id.btnSaveChange))
            .check(matches(isDisplayed()))
            .perform(click())

        pressBack()
        pressBack()

        onView(withId(R.id.rvContacts))
            .check(matches(
                CustomMatchers.atPosition(
                    0,
                    hasDescendant(withText("Alex Shrerder"))
                )
            ))
            .check(matches(CustomMatchers.atPosition(0, hasDescendant(withText("9990245790")))))

    }
}