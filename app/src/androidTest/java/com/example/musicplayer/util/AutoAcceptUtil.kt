package com.example.musicplayer.util

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matchers.allOf
import com.example.musicplayer.R // Ensure R is imported correctly

object AutoAcceptUtil {
    var autoAcceptEnabled = true // Default to enabled for tests, can be configured

    const val TAG = "AutoAcceptUtil"

    /**
     * Checks for a dialog with an "Accept" button (specifically R.id.button_accept)
     * and clicks it if autoAcceptEnabled is true.
     *
     * This is a simple example and might need to be made more robust or specific
     * if multiple dialogs use the same button ID, or if dialogs appear unexpectedly.
     * Consider adding checks for dialog titles or messages if necessary.
     */
    fun handleMockTermsDialog() {
        if (autoAcceptEnabled) {
            try {
                // Check if the dialog's accept button is displayed and click it
                onView(allOf(withId(R.id.button_accept), isDisplayed()))
                    .perform(click())
                Log.i(TAG, "Mock terms dialog automatically accepted via R.id.button_accept.")
            } catch (e: Exception) {
                // Dialog not found or not displayed, which is fine.
                // This could be a NoMatchingViewException or AmbiguousViewMatcherException if not handled.
                Log.d(TAG, "Mock terms dialog (R.id.button_accept) not found or auto-accept not triggered: ${e.message}")
            }
        } else {
            Log.d(TAG, "Auto-accept for mock terms dialog is disabled.")
        }
    }

    // It might be useful to have a more generic version or specific handlers for other dialogs
    // fun handleDialog(dialogButtonId: Int, dialogName: String = "dialog") {
    //     if (autoAcceptEnabled) {
    //         try {
    //             onView(allOf(withId(dialogButtonId), isDisplayed())).perform(click())
    //             Log.i(TAG, "$dialogName with button ID $dialogButtonId automatically handled.")
    //         } catch (e: Exception) {
    //             Log.d(TAG, "$dialogName with button ID $dialogButtonId not found or auto-handle not triggered: ${e.message}")
    //         }
    //     } else {
    //         Log.d(TAG, "Auto-handle for $dialogName is disabled.")
    //     }
    // }
}
