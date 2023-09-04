package com.example.sudoku

import android.util.Log
import kotlin.random.Random

object SudokuMain {
    private const val sheetLength = 9
     var answerSheet = CustomArrayList<CustomArrayList<Int>>()



    init{
        CreateSheet()
    }

    private fun CreateSheet(){
        val oneToNine = (1..9).toMutableList()
        var list = CustomArrayList<Int>()
        list.addAll(oneToNine)

        answerSheet.add(list)
        for (i in 1..2){
            list = list.clone()
            list.shiftLeft(3)
            answerSheet.add(list)
        }

        var copySheet = answerSheet.clone()
        for (i in copySheet){
            list = i.clone()
            list.shiftLeft(1)
            answerSheet.add(list)
        }
        for (i in copySheet){
            list = i.clone()
            list.shiftLeft(2)
            answerSheet.add(list)
        }
        var randomSwapCount = Random.nextInt(20, 40)

        for(i in 0..randomSwapCount){
            swapRandomCols()
            swapRandomRows()
            if(i % 2 == 0){
                answerSheet = transpose()
            }
        }
    }

    private fun transpose(): CustomArrayList<CustomArrayList<Int>> {
        var newAnswerSheet = CustomArrayList<CustomArrayList<Int>>()
        for(i in 0 until answerSheet.size)
            newAnswerSheet.add(CustomArrayList())

        for(i in 0 until answerSheet.size){
            for(j in 0 until answerSheet.size){
                newAnswerSheet[j].add(answerSheet[i][j])
            }
        }
        return newAnswerSheet
    }

    private fun swapRandomRows(){
        val firstRandom = Random.nextInt(6,9)
        var secondRandom = Random.nextInt(6,9)
        while(firstRandom == secondRandom){
            secondRandom = Random.nextInt(6,9)
        }
        val rowCoef = Random.nextInt(0,3)

        val firstRowIndex = firstRandom - (rowCoef * 3)
        val secondRowIndex = secondRandom - (rowCoef * 3)

        var firstRow = answerSheet[firstRowIndex].clone()
        answerSheet[firstRowIndex] = answerSheet[secondRowIndex].clone()
        answerSheet[secondRowIndex]=firstRow

    }
    private fun swapRandomCols(){
        val firstRandom = Random.nextInt(6,9)
        var secondRandom = Random.nextInt(6,9)
        while(firstRandom == secondRandom){
            secondRandom = Random.nextInt(6,9)
        }
        val colCoef = Random.nextInt(0,3)

        val firstColIndex = firstRandom - (colCoef * 3)
        val secondColIndex = secondRandom - (colCoef * 3)

        for (i in 0 until 9){
            var firstCol = answerSheet[i][firstColIndex]
            answerSheet[i][firstColIndex]= answerSheet[i][secondColIndex]
            answerSheet[i][secondColIndex]=firstCol
        }
    }

    fun LogAnswerSheet(){

        for(row in 0 until answerSheet.size){
            var rowStr = ""
            if(row % 3 == 0) {
                Log.d("Kristex", " ")
            }
            for (value in 0 until  answerSheet.size) {
                if(value % 3 == 0 ){
                    rowStr += "  "
                }
                rowStr += answerSheet[row][value].toString() + " "

            }
            Log.d("Kristex", rowStr)
        }
    }
}