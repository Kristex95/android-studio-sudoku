package com.example.sudoku

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sudoku.ui.theme.SudokuTheme

const val TAG = "Andrii"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SudokuMain.LogAnswerSheet()



        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        var answerSheet = SudokuMain.answerSheet


        for (row in 0 until 9) {
            for (col in 0 until 9) {
                val buttonLayout = LayoutInflater.from(this)
                    .inflate(R.layout.button_grid_item, gridLayout, false)

                val button = buttonLayout.findViewById<Button>(R.id.button)
                var buttText = ""

                buttText+=answerSheet[row][col].toString()
                Log.d("Kristex", buttText)
                button.tag=buttText
                button.text ="$buttText"  // Set button text as per your requirement
                buttText=""


                // Add click listener to the button
                button.setOnClickListener { view ->
                    val tag = view.tag as String
                    // Handle button click here
                }

                // Add the button to the GridLayout
                gridLayout.addView(buttonLayout)
            }
        }
    }
}