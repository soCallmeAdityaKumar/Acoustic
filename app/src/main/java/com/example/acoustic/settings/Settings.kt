package com.example.acoustic.settings

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Settings() {
    Box(modifier = Modifier.fillMaxSize()
        .padding(top = 40.dp)){
        Text(text="Settings", modifier = Modifier.fillMaxWidth().padding(top = 40.dp), fontSize = MaterialTheme.typography.h1.fontSize)
    }
}