package com.example.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.core.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.nav_host))

        checkActionbar()
    }

    private fun checkActionbar() {
        findNavController(R.id.nav_host).addOnDestinationChangedListener { _, destination, _ ->
            if (
                destination.id == R.id.tutorialFragment ||
                destination.id == R.id.loginFragment ||
                destination.id == R.id.splashFragment
            ) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}