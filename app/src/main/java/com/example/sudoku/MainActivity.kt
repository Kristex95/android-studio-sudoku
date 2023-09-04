package com.example.sudoku

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import java.util.Stack

const val TAG = "Andrii"

class MainActivity : ComponentActivity() {
    var selectedButton: Button? = null
    private val color1 = R.color.light_wight
    private val color2 = R.color.pumpkin
    private var currentColor = color1
    @SuppressLint("ResourceAsColor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonReset: Button = findViewById(R.id.button_reset)
        val button1: Button = findViewById(R.id.butt1)
        val button2: Button = findViewById(R.id.butt2)
        val button3: Button = findViewById(R.id.butt3)
        val button4: Button = findViewById(R.id.butt4)
        val button5: Button = findViewById(R.id.butt5)
        val button6: Button = findViewById(R.id.butt6)
        val button7: Button = findViewById(R.id.butt7)
        val button8: Button = findViewById(R.id.butt8)
        val button9: Button = findViewById(R.id.butt9)
        val buttonUndo: Button = findViewById(R.id.buttonUndo)
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

        val moveStack = Stack<Move>()

        SudokuMain.LogAnswerSheet()



        fun setNumbersInSudoku(row: Int, col: Int, button: Button) {
            var buttText = ""
            var answerSheet = SudokuMain.answerSheet
            val move = Move(row, col, answerSheet[row][col])
            moveStack.push(move)
            buttText += answerSheet[row][col].toString()
            button.tag = buttText
            button.text = "$buttText"

        }

        fun undoLastMove() {
            Log.d("Kristex", "Вызвана функция undoLastMove()")
            if (moveStack.isNotEmpty()) {
                val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
                val lastMove = moveStack.pop()
                val button = gridLayout.getChildAt(lastMove.row * 9 + lastMove.col) as Button
                button.text = lastMove.value.toString()
                // Можете также обновить цвет фона или другие свойства, если необходимо
            } else {
                Toast.makeText(this, "Нет доступных ходов для отмены", Toast.LENGTH_SHORT).show()
            }
        }

        fun resetGame() {
            val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
            for (row in 0 until 9) {
                for (col in 0 until 9) {
                    val button = gridLayout.getChildAt(row * 9 + col) as Button
                    setNumbersInSudoku(row, col, button)
                }
            }
        }

        fun assignNumpadClick() {
            buttons.forEach { button ->
                button.setOnClickListener {
                    /*selectedButton = button*/
                    when (button) {
                        button1 -> {
                            selectedButton?.text = button1.text
                        }

                        button2 -> {
                            selectedButton?.text = button2.text
                        }

                        button3 -> {
                            selectedButton?.text = button3.text
                        }

                        button4 -> {
                            selectedButton?.text = button4.text
                        }

                        button5 -> {
                            selectedButton?.text = button5.text
                        }

                        button6 -> {
                            selectedButton?.text = button6.text
                        }

                        button7 -> {
                            selectedButton?.text = button7.text
                        }

                        button8 -> {
                            selectedButton?.text = button8.text
                        }

                        button9 -> {
                            selectedButton?.text = button9.text
                        }
                    }
                }
            }
        }

        buttonUndo.setOnClickListener {
            undoLastMove()
        }

        buttonReset.setOnClickListener {
            resetGame()
        }


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
                buttonInList.setBackgroundColor(R.color.black)


                buttonInList.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, currentColor)
                )

                setNumbersInSudoku(row, col, buttonInList)

                //save clicked button
                buttonInList.setOnClickListener {
                    selectedButton = buttonInList
                }


                currentColor = if (currentColor == color1) color2 else color1
                // Add the button to the GridLayout
                gridLayout.addView(buttonLayout)
            }
        }

        assignNumpadClick()

    }
}