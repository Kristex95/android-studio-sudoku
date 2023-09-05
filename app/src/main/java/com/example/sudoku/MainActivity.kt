package com.example.sudoku

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import java.util.Stack

const val TAG = "Andrii"

class MainActivity : ComponentActivity() {


    @SuppressLint("StaticFieldLeak")
    object Selected {
        var button: Button? = null
        var row: Int = 0
        var col: Int = 0
        var originalBgColor = Color.WHITE
    }

    private val color1 = R.color.light_wight
    private val color2 = R.color.light_pink
    private var currentColor = color1
    private var playersMoves = Stack<ButtonData>()
    private final val undoTimes = 20
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


        SudokuMain.LogAnswerSheet()



        fun setNumbersInSudoku(row: Int, col: Int, button: Button) {
            var buttText = ""
            if (SudokuMain.playerSheet[row][col]!=0){
                buttText += SudokuMain.playerSheet[row][col].toString()
                button.setTextColor(Color.BLUE)
            }

            button.tag = buttText
            button.text = "$buttText"

        }

        fun isEditableButton(row: Int, col: Int) : Boolean{
            if(SudokuMain.playerSheet[row][col]==0){
                return true
            }
            return false
        }

        fun undoLastMove() {
            if (playersMoves.isNotEmpty()) {
                val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
                val lastMove = playersMoves.pop()
                val button = lastMove.button
                button?.text = lastMove.value.toString()
                if(lastMove.value != "")
                    SudokuMain.playerSheet[lastMove.row][lastMove.col] = Integer.parseInt(lastMove.value)
                else{
                    SudokuMain.playerSheet[lastMove.row][lastMove.col] = 0
                }
                // Можете также обновить цвет фона или другие свойства, если необходимо
            } else {
                Toast.makeText(this, "Нет доступных ходов для отмены", Toast.LENGTH_SHORT).show()
            }
        }
        fun resetToDefaultValues(){
            Selected.button=null
            playersMoves.clear()
        }





        fun assignNumpadClick() {
            buttons.forEach { button ->
                button.setOnClickListener {
                    if(Selected.button == null){
                        return@setOnClickListener
                    }
                    if(Selected.button?.text != button.text){
                        if(playersMoves.size >= undoTimes){
                            playersMoves.removeAt(0)
                        }
                        playersMoves.add(ButtonData(Selected.row, Selected.col, Selected.button, Selected.button?.text.toString()))
                    }
                    /*selectedButton = button*/
                    when (button) {
                        button1 -> {
                            Selected.button?.text = button1.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button1.text.toString())
                        }

                        button2 -> {
                            Selected.button?.text = button2.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button2.text.toString())
                        }

                        button3 -> {
                            Selected.button?.text = button3.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button3.text.toString())
                        }

                        button4 -> {
                            Selected.button?.text = button4.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button4.text.toString())
                        }

                        button5 -> {
                            Selected.button?.text = button5.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button5.text.toString())
                        }

                        button6 -> {
                            Selected.button?.text = button6.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button6.text.toString())
                        }

                        button7 -> {
                            Selected.button?.text = button7.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button7.text.toString())
                        }

                        button8 -> {
                            Selected.button?.text = button8.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button8.text.toString())
                        }

                        button9 -> {
                            Selected.button?.text = button9.text
                            SudokuMain.playerSheet[Selected.row][Selected.col] = Integer.parseInt(button9.text.toString())
                        }
                    }
                    if(SudokuMain.answerSheet == SudokuMain.playerSheet){
                        Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        fun createSudokuGame() {
            val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
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
                    if (isEditableButton(row, col)) {
                        buttonInList.setOnClickListener {
                            Selected.button?.setBackgroundColor(Selected.originalBgColor)

                            Selected.button = buttonInList
                            Selected.row = row
                            Selected.col = col
                            Selected.originalBgColor =
                                (buttonInList.background as ColorDrawable).color
                            buttonInList.setBackgroundColor(Color.BLUE)
                        }
                    } else {
                        buttonInList.setOnClickListener {
                            return@setOnClickListener
                        }
                    }

                    // Add the button to the GridLayout
                    gridLayout.addView(buttonLayout)
                }
            }

            assignNumpadClick()
        }

        createSudokuGame()


        fun resetGame() {
            val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
            gridLayout.removeAllViews() // Удаляем все представления из GridLayout
            SudokuMain.createNewGame()
            /*resetToDefaultValues()
            createSudokuGame()*/
            resetToDefaultValues()
            createSudokuGame()
        }

        buttonUndo.setOnClickListener {
            undoLastMove()
        }

        buttonReset.setOnClickListener {
            resetGame()
        }


    }
}