package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewStory()
        }
    }

    @Composable
    fun NewStory(){
        Column {
            Text(text = "This is line number 1 in coloumn layout")
            Text(text = "This is line number 2 in coloumn layout")
            Text(text = "This is line number 3 in coloumn layout")
        }
    }

    @Preview
    @Composable
    fun demo(){
        NewStory()
    }
}