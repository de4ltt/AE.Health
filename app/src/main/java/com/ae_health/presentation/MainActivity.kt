package com.ae_health.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ae_health.presentation.ui.navigation.Navigation
import com.ae_health.presentation.ui.theme.AEHealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AEHealthTheme {
                Navigation()
            }
        }
    }
}