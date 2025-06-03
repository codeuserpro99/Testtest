package com.example.musicplayer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.musicplayer.util.AutoAcceptUtil
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainActivityBasicUITest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Ensure tests run in a predictable state, especially for AutoAcceptUtil
        // This could also be done in @After, or on a per-test basis if needed.
        AutoAcceptUtil.autoAcceptEnabled = true // Default to true before each test, can be overridden in specific tests.
    }

    @Test
    fun testMainActivityUIAccessibility() {
        // Check if the "Now Playing" title is displayed (from header_bar.xml)
        onView(withId(R.id.title_textview)).check(matches(withText("Now Playing")))

        // Check if the play/pause button is displayed
        onView(withId(R.id.play_pause_button)).check(matches(isDisplayed()))

        // Check if the bottom navigation is displayed
        onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))

        // Check if the "Show Mock Dialog" button is present
        onView(withId(R.id.show_mock_dialog_button)).check(matches(isDisplayed()))
    }

    @Test
    fun testAutoAcceptMockDialog_Enabled() {
        AutoAcceptUtil.autoAcceptEnabled = true // Explicitly enable for this test

        // Click the button to show the dialog
        onView(withId(R.id.show_mock_dialog_button)).perform(click())

        // Call the auto-accept utility
        AutoAcceptUtil.handleMockTermsDialog()

        // Verify the dialog is dismissed (accept button no longer found/displayed)
        onView(withId(R.id.button_accept)).check(doesNotExist())
    }

    @Test
    fun testAutoAcceptMockDialog_Disabled_UserAccepts() {
        AutoAcceptUtil.autoAcceptEnabled = false // Explicitly disable for this test

        // Click the button to show the dialog
        onView(withId(R.id.show_mock_dialog_button)).perform(click())

        // Call the auto-accept utility (it should do nothing other than log)
        AutoAcceptUtil.handleMockTermsDialog()

        // Verify the "Accept" button is still displayed (because auto-accept was off)
        onView(withId(R.id.button_accept)).check(matches(isDisplayed()))

        // Manually click "Accept" in the test
        onView(withId(R.id.button_accept)).perform(click())

        // Verify the dialog is dismissed
        onView(withId(R.id.button_accept)).check(doesNotExist())
    }

    @Test
    fun testAutoAcceptMockDialog_Disabled_UserDeclines() {
        AutoAcceptUtil.autoAcceptEnabled = false // Explicitly disable for this test

        // Click the button to show the dialog
        onView(withId(R.id.show_mock_dialog_button)).perform(click())

        // Call the auto-accept utility (it should do nothing other than log)
        AutoAcceptUtil.handleMockTermsDialog()

        // Verify the "Decline" button is still displayed
        onView(withId(R.id.button_decline)).check(matches(isDisplayed()))

        // Manually click "Decline" in the test
        onView(withId(R.id.button_decline)).perform(click())

        // Verify the dialog is dismissed (decline button should also not exist)
        onView(withId(R.id.button_decline)).check(doesNotExist())
    }
}
