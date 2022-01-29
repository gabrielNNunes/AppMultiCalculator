package com.example.multicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        imageButton_calculator.setOnClickListener {
            startActivity(Intent(this, Calculator::class.java))
        }

        imageButton_geometricShapes.setOnClickListener {
            startActivity(Intent(this, GeometricShapes::class.java))
        }
        imageButton_IMC.setOnClickListener {
            startActivity(Intent(this, Imc_Calculator::class.java))
        }


    }
}
