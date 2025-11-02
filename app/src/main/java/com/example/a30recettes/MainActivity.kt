package com.example.a30recettes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a30recettes.data.recettesList
import com.example.a30recettes.ui.theme._30RecettesTheme
import com.example.a30recettes.ui.theme.RecetteScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30RecettesTheme {
                RecetteScreen(recettes = recettesList)
            }
        }
    }
}
