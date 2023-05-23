package com.example.realmdatabase

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatchers {

    companion object {

        fun withHint(expectedHint: String): Matcher<View> =
            object : BoundedMatcher<View, EditText>(EditText::class.java) {

                override fun describeTo(description: Description?) {
                    description?.appendText("EditText with expected hint: $expectedHint")
                }

                override fun matchesSafely(item: EditText?): Boolean {
                    if (item == null) {
                        return false
                    }
                    val hint = item.hint
                    return expectedHint == hint
                }
            }

        fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> =
            object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("RecyclerView with position: $position")
                }

                override fun matchesSafely(item: RecyclerView?): Boolean {
                    if (item == null) {
                        return false
                    }

                    val viewHolder = item.findViewHolderForAdapterPosition(position) ?: return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
    }
}