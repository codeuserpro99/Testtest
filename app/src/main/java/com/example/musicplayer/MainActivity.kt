package com.example.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android:os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.musicplayer.databinding.ActivityMainBinding // Import generated binding class

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of accessing a view via binding
        // binding.titleTextview.text = "Now Playing from Code" // Temporarily set for demonstration
        // binding.titleTextview.text = "Now Playing" // Reset to original or leave as is from XML

        binding.showMockDialogButton.setOnClickListener {
            showMockTermsDialog()
        }

        // Example of setting an OnClickListener
        // binding.playPauseButton.setOnClickListener {
        //    // Handle play/pause click
        //    // For now, maybe just a Log statement or a Toast
        //    // android.widget.Toast.makeText(this, "Play/Pause clicked", android.widget.Toast.LENGTH_SHORT).show()
        // }

        // Accessing header bar elements
        // The header bar is included in activity_main.xml. View Binding handles this by creating
        // a property for the included layout if the <include> tag has an ID.
        // If header_bar.xml's root <RelativeLayout> had an id, e.g. android:id="@+id/headerBarLayout",
        // and the include tag was <include android:id="@+id/includedHeader" layout="@layout/header_bar"/>,
        // then you could potentially do binding.includedHeader.backArrowButton (if backArrowButton is in header_bar.xml).
        // However, our <include> tag does not have an ID. In this common case, views from the included layout
        // are merged into the parent layout's binding object *if their IDs are unique*.
        // So, binding.backArrowButton should work directly if "back_arrow_button" is unique.
        binding.backArrowButton.setOnClickListener {
            // Example action: For now, just log, or use finish() if it were a real back button.
            Log.d("MainActivity", "Back arrow clicked in header.")
            // finish()
        }

        // Accessing bottom navigation view
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Log.d("MainActivity", "Home navigation item selected.")
                    // Handle home navigation
                    true
                }
                R.id.nav_search -> {
                    Log.d("MainActivity", "Search navigation item selected.")
                    // Handle search navigation
                    true
                }
                R.id.nav_library -> {
                    Log.d("MainActivity", "Library navigation item selected.")
                    // Handle library navigation
                    true
                }
                R.id.nav_profile -> {
                    Log.d("MainActivity", "Profile navigation item selected.")
                    // Handle profile navigation
                    true
                }
                else -> false
            }
        }
    }

    private fun showMockTermsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_mock_terms, null)
        val acceptButton = dialogView.findViewById<Button>(R.id.button_accept)
        val declineButton = dialogView.findViewById<Button>(R.id.button_decline)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Mock Terms")
            .setView(dialogView)
            .setCancelable(false) // User must explicitly accept or decline
            .create()

        acceptButton.setOnClickListener {
            Log.d("MockDialog", "Accepted by user.")
            // Here you might set a flag in SharedPreferences or similar
            dialog.dismiss()
        }
        declineButton.setOnClickListener {
            Log.d("MockDialog", "Declined by user.")
            // Handle decline, e.g., show another message or close the app
            dialog.dismiss()
        }
        dialog.show()
    }
}
