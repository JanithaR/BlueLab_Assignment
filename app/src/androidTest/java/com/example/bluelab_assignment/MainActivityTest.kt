package com.example.bluelab_assignment


import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun input1AndInput2ValueChange() {
        val editTextInput1 = onView(
            allOf(
                withId(R.id.editTextInput1),
                isDisplayed()
            )
        )

        editTextInput1.perform(click())

        editTextInput1.perform(replaceText("1"), closeSoftKeyboard())

        val editTextInput2 = onView(
            allOf(
                withId(R.id.editTextInput2),
                isDisplayed()
            )
        )

        editTextInput2.check(matches(withText("1.0")))

        editTextInput1.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(50)

        editTextInput2.perform(replaceText("2"))

        editTextInput2.perform(closeSoftKeyboard())

        editTextInput1.check(matches(withText("2.0")))
    }

    @Test
    fun mainActivityInputFieldsAndInitialValues() {
        val spinner = onView(
            allOf(
                withId(R.id.spinnerCategory),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        spinner.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(android.R.id.text1), withText("Temperature"),
                childAtPosition(
                    allOf(
                        withId(R.id.spinnerCategory),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Temperature")))

        val editText = onView(
            allOf(
                withId(R.id.editTextInput1),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editText.check(matches(isDisplayed()))

        val editText2 = onView(
            allOf(
                withId(R.id.editTextInput1),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView)
            )
        )
        textView2.check(matches(withText("=")))

        val editText3 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )
        editText3.check(matches(isDisplayed()))

        val editText4 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )
        editText4.check(matches(withText("")))

        val spinner2 = onView(
            allOf(
                withId(R.id.spinnerUnit1),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        spinner2.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(android.R.id.text1), withText("Kelvin"),
                childAtPosition(
                    allOf(
                        withId(R.id.spinnerUnit1),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            4
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Kelvin")))

        val spinner3 = onView(
            allOf(
                withId(R.id.spinnerUnit2),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        spinner3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(android.R.id.text1), withText("Kelvin"),
                childAtPosition(
                    allOf(
                        withId(R.id.spinnerUnit2),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            5
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Kelvin")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
