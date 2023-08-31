package com.example.sudoku

class CustomArrayList<T> : ArrayList<T>() {


    fun shiftLeft(shift: Int): CustomArrayList<T> {
        if(shift < 0){
            throw Exception("shift can't be less than 1")
        }

        for(i in 0 until shift){
            var num = get(0)
            add(num)
            removeAt(0)
        }

        return this
    }

    override fun clone(): CustomArrayList<T> {
        var copyList : CustomArrayList<T> = CustomArrayList<T>()
        copyList.addAll(this)
        return copyList
    }
}