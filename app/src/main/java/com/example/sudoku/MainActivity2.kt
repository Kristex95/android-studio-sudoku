package com.example.sudoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonEasy=findViewById<Button>(R.id.button2)
        val buttonMedium=findViewById<Button>(R.id.button3)
        val buttonHard=findViewById<Button>(R.id.button4)


        fun createIntent(test:String,name:String,code:Int){
            val intent = Intent (this,MainActivity::class.java)
            intent.putExtra(test,name)
            startActivityForResult(intent,code)


        }

        buttonEasy.setOnClickListener {
            createIntent(Constance.TEST,Constance.DIFFICULT_EASY,Constance.REQUEST_CODE_EASY)
        }

        buttonMedium.setOnClickListener {
            createIntent(Constance.TEST,Constance.DIFFICULT_MEDIUM,Constance.REQUEST_CODE_MEDIUM)
        }

        buttonHard.setOnClickListener {
            createIntent(Constance.TEST,Constance.DIFFICULT_HARD,Constance.REQUEST_CODE_HARD)
        }


    }
}