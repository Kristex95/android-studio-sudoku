package com.example.sudoku

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.sudoku.ui.theme.SudokuTheme

const val TAG = "Andrii"

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceAsColor")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SudokuMain.LogAnswerSheet()
        val color1 = R.color.light_wight
        val color2 = R.color.pumpkin
        var currentColor = color1


        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        var answerSheet = SudokuMain.answerSheet
        var paintRow=true
        var paintCol=true
        for (row in 0 until 9) {
            if(row%3==0)paintRow=!paintRow
            paintCol=true
                for (col in 0 until 9) {
                    if (col%3==0)paintCol=!paintCol
                    if (paintRow != paintCol)currentColor=color1 else currentColor=color2
                    val buttonLayout = LayoutInflater.from(this)
                        .inflate(R.layout.button_grid_item, gridLayout, false)

                    val button = buttonLayout.findViewById<Button>(R.id.button)
                    var buttText = ""
                    button.setBackgroundColor(R.color.black)


                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, currentColor)
                    )
                    buttText += answerSheet[row][col].toString()
                    Log.d("Kristex", buttText)
                    button.tag = buttText
                    button.text = "$buttText"  // Set button text as per your requirement
                    buttText = ""



                    button.setOnClickListener {

                        val buttonText = (it as Button).text.toString()
                        var number = buttonText.toInt()


                        number = (number + 1) % 10


                        button.text = number.toString()
                        Toast.makeText(this, "Вы нажали кнопку $buttonText", Toast.LENGTH_SHORT).show()
                    }


                    currentColor = if (currentColor == color1) color2 else color1
                    // Add the button to the GridLayout
                    gridLayout.addView(buttonLayout)
                }
        }
    }
}