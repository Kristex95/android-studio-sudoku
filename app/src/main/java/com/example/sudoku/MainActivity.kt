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
import androidx.core.content.ContextCompat

const val TAG = "Andrii"

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceAsColor")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.butt1)
        val button2: Button = findViewById(R.id.butt2)
        val button3: Button = findViewById(R.id.butt3)
        val button4: Button = findViewById(R.id.butt4)
        val button5: Button = findViewById(R.id.butt5)
        val button6: Button = findViewById(R.id.butt6)
        val button7: Button = findViewById(R.id.butt7)
        val button8: Button = findViewById(R.id.butt8)
        val button9: Button = findViewById(R.id.butt9)

        val buttons = listOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9
        )
        var selectedButton: Button? = null


        SudokuMain.LogAnswerSheet()
        val color1 = R.color.light_wight
        val color2 = R.color.pumpkin
        var currentColor = color1


        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        var answerSheet = SudokuMain.answerSheet
        var paintRow = true
        var paintCol = true
        for (row in 0 until 9) {
            if (row % 3 == 0) paintRow = !paintRow
            paintCol = true
            for (col in 0 until 9) {
                if (col % 3 == 0) paintCol = !paintCol
                if (paintRow != paintCol) currentColor = color1 else currentColor = color2
                val buttonLayout = LayoutInflater.from(this)
                    .inflate(R.layout.button_grid_item, gridLayout, false)

                val buttonInList = buttonLayout.findViewById<Button>(R.id.button)
                var buttText = ""
                buttonInList.setBackgroundColor(R.color.black)


                buttonInList.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, currentColor)
                )
                buttText += answerSheet[row][col].toString()
                Log.d("Kristex", buttText)
                buttonInList.tag = buttText
                buttonInList.text = "$buttText"  // Set button text as per your requirement
                buttText = ""

                buttons.forEach { button ->
                    button.setOnClickListener {
                        selectedButton = button
                        }
                    }
                        buttonInList.setOnClickListener {

                            var buttonText = (it as Button).text.toString()
                            if (selectedButton != null) {
                                buttonInList.text = selectedButton!!.text
                                Toast.makeText(this, "Текст кнопки изменен на $buttonText", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Выберите кнопку из списка сначала", Toast.LENGTH_SHORT).show()
                            }
                        }





                currentColor = if (currentColor == color1) color2 else color1
                // Add the button to the GridLayout
                gridLayout.addView(buttonLayout)
            }
        }
    }
}