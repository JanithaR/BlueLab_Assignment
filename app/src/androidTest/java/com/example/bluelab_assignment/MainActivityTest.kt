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

    @Test
    fun changingTheCategoryToVolume() {
        val spinnerCategory = onView(
            allOf(
                withId(R.id.spinnerCategory)
            )
        )

        spinnerCategory.perform(click())

        val appCompatCheckedTextView = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)

        appCompatCheckedTextView.perform(click())

        val editTextInput1 = onView(
            allOf(
                withId(R.id.editTextInput1)
            )
        )
        editTextInput1.check(matches(withText("")))

        val editTextInput2 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )
        editTextInput2.check(matches(withText("")))

        val textView = onView(
            allOf(
                withId(android.R.id.text1), withText("Litre"),
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
        textView.check(matches(withText("Litre")))

        val textView2 = onView(
            allOf(
                withId(android.R.id.text1), withText("Litre"),
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
        textView2.check(matches(withText("Litre")))
    }

    @Test
    fun convertingKelvinToOthers() {
        val appCompatEditText = onView(
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
        appCompatEditText.perform(click())

        val appCompatEditText2 = onView(
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
        appCompatEditText2.perform(replaceText("100"), closeSoftKeyboard())

        val appCompatSpinner = onView(
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
        appCompatSpinner.perform(click())

        val appCompatCheckedTextView = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
        appCompatCheckedTextView.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.editTextInput2), withText("-173.15")
            )
        )
        editText.check(matches(withText("-173.15")))

        val appCompatSpinner2 = onView(
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
        appCompatSpinner2.perform(click())

        val appCompatCheckedTextView2 = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(2)
        appCompatCheckedTextView2.perform(click())

        val editText2 = onView(
            allOf(
                withId(R.id.editTextInput2), withText("-279.67")
            )
        )
        editText2.check(matches(withText("-279.67")))
    }

    @Test
    fun convertingCelsiusToOthers() {
        val appCompatSpinner = onView(
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
        appCompatSpinner.perform(click())

        val appCompatCheckedTextView = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
        appCompatCheckedTextView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextInput1), withText("0.0"),
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
        appCompatEditText.perform(replaceText("100"))

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editTextInput1), withText("100"),
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
        appCompatEditText2.perform(closeSoftKeyboard())

        val editText = onView(
            allOf(
                withId(R.id.editTextInput2), withText("373.15")
            )
        )
        editText.check(matches(withText("373.15")))

        val appCompatSpinner2 = onView(
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
        appCompatSpinner2.perform(click())

        val appCompatCheckedTextView2 = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(2)
        appCompatCheckedTextView2.perform(click())

        val editText2 = onView(
            allOf(
                withId(R.id.editTextInput2), withText("212.0")
            )
        )
        editText2.check(matches(withText("212.0")))
    }

    @Test
    fun convertingFahrenheitToOthers() {
        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spinnerUnit1)
            )
        )

        appCompatSpinner.perform(click())

        val appCompatCheckedTextView = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(2)

        appCompatCheckedTextView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextInput1)
            )
        )
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard())

        val editText = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )

        editText.check(matches(withText("310.93")))

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.spinnerUnit2)
            )
        )
        appCompatSpinner2.perform(click())

        val appCompatCheckedTextView2 = Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
        appCompatCheckedTextView2.perform(click())

        val editText2 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )
        editText2.check(matches(withText("37.78")))
    }

    @Test
    fun convertingLitresToOthers() {
        // open Category spinner
        onView(
            allOf(
                withId(R.id.spinnerCategory)
            )
        ).perform(click())

        // select volume
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
            .perform(click())

        // enter 100 to input 1
        val editTextUnit1 = onView(
            allOf(
                withId(R.id.editTextInput1)
            )
        )

        editTextUnit1.perform(replaceText("100"), closeSoftKeyboard())

        // open unit 2 spinner
        val spinnerUnit2 = onView(
            allOf(
                withId(R.id.spinnerUnit2)
            )
        ).perform(click())

        // select millilitres
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
            .perform(click())

        // assert input 2
        val editTextUnit2 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )

        editTextUnit2.check(matches(withText("100000.0")))

        // open unit 2 spinner
        spinnerUnit2.perform(click())

        // select us liquid gallons
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(2)
            .perform(click())

        // assert input 2
        editTextUnit2.check(matches(withText("26.42")))
    }

    @Test
    fun convertingMillilitresToOthers() {
        // open category spinner
        onView(
            allOf(
                withId(R.id.spinnerCategory)
            )
        ).perform(click())

        // select volume
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
            .perform(click())

        // open unit 1 spinner
        onView(
            allOf(
                withId(R.id.spinnerUnit1)
            )
        ).perform(click())

        // select millilitre
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
            .perform(click())

        // enter 100 to input 1
        onView(
            allOf(
                withId(R.id.editTextInput1)
            )
        ).perform(replaceText("100"))

        // assert input 2
        val editTextUnit2 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )

        editTextUnit2.check(matches(withText("0.1")))

        // open unit 2 spinner
        onView(
            allOf(
                withId(R.id.spinnerUnit2)
            )
        ).perform(click())

        // select us liquid gallons
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(2)
            .perform(click())

        // assert input 2
        editTextUnit2.check(matches(withText("0.03")))
    }

    @Test
    fun convertingGallonsToOthers() {
        // open category spinner
        onView(
            allOf(
                withId(R.id.spinnerCategory)
            )
        ).perform(click())

        // select volume
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
            .perform(click())

        // open unit 1 spinner
        onView(
            allOf(
                withId(R.id.spinnerUnit1)
            )
        ).perform(click())

        // select gallons
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(2)
            .perform(click())

        // enter 100 to input 1
        val editTextUnit1 = onView(
            allOf(
                withId(R.id.editTextInput1)
            )
        )

        editTextUnit1.perform(replaceText("100"))

        // assert input 2
        val editTextInput2 = onView(
            allOf(
                withId(R.id.editTextInput2)
            )
        )

        editTextInput2.check(matches(withText("378.5")))

        // open unit 2 spinner
        onView(
            allOf(
                withId(R.id.spinnerUnit2)
            )
        ).perform(click())

        // select millilitre
        Espresso.onData(Matchers.anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(Matchers.endsWith("PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
            .perform(click())

        // assert input 2
        editTextInput2.check(matches(withText("378541.2")))
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
