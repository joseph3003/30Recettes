package com.example.a30recettes.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30recettes.model.Recette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecetteScreen(recettes: List<Recette>) {
    Scaffold(
        topBar = {
            // 🌅 Barre supérieure avec dégradé
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFFff9a9e), Color(0xFFfad0c4))
                        )
                    )
                    .padding(vertical = 18.dp)
            ) {
                Text(
                    text = "🍽️ 30 jours de recettes",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->

        // 🌿 Arrière-plan clair
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F6F3))
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(recettes) { recette ->
                    RecetteCard(recette)
                }
            }
        }
    }
}

@Composable
fun RecetteCard(recette: Recette) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // 🧱 En-tête (jour + titre + flèche)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Jour ${recette.jour}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color(0xFFEF6C00),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = recette.titre,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF37474F)
                        )
                    )
                }

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) "Réduire" else "Afficher",
                        tint = Color(0xFFEF6C00)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🖼️ Image de la recette
            Image(
                painter = painterResource(id = recette.imageRes),
                contentDescription = recette.titre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            // 📝 Description visible si expanded = true
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = recette.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = Color(0xFF616161),
                        lineHeight = 22.sp
                    ),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}
