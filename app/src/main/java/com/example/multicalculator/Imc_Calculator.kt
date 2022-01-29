package com.example.multicalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_imc_calculator.*
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.max

class Imc_Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)


        //16 a 16,9 kg/m² - Muito abaixo do peso
        // 17 a 18,4 kg/m² - Abaixo do peso
        // 18,5 a 24,9 kg/m² - Peso normal
        // 25 a 29,9 kg/m² - Acima do peso
        // 30 a 34,9 kg/m² - Obesidade grau I
        // 35 a 40 kg/m² - Obesidade grau II
        // maior que 40 kg/m² - Obesidade grau III...
        //https://www.uol.com.br/vivabem/faq/imc-como-calcular-tabela-dicas-como-melhorar-e-mais.htm


        button_calcularIMC.setOnClickListener {

            val altura: String = editTextNumber_altura.text.toString().replace(",",".")
            val peso: String = editTextNumber_peso.text.toString().replace(",",".")

            if(altura.isEmpty() || peso.isEmpty()) {
                Toast.makeText(this,"Há informações não preenchidas",Toast.LENGTH_LONG).show()
            }else{

                val expression: String = "$peso/($altura*$altura)"

                val calc = ExpressionBuilder(expression).build()
                val result = calc.evaluate()


                when (result) {
                    in 16.0..16.9 -> textView_muito_abaixo_peso_table.setBackgroundColor(Color.LTGRAY)
                    in 17.0..18.4 -> textView_abaixo_peso_table.setBackgroundColor(Color.LTGRAY)
                    in 18.5..24.9 -> textView_peso_normal_table.setBackgroundColor(Color.LTGRAY)
                    in 25.0..29.9 -> textView_acima_peso_table.setBackgroundColor(Color.LTGRAY)
                    in 30.0..34.9 -> textView_obesidadeI_table.setBackgroundColor(Color.LTGRAY)
                    in 35.0..40.0 -> textView_obesidadeII_table.setBackgroundColor(Color.LTGRAY)
                    else -> {
                        if (result > 40.0) {
                            textView_obesidadeIII_table.setBackgroundColor(Color.LTGRAY)
                        }
                    }
                }

                textView_resultScreen.text = String.format("%.2f kg/m²", result)




                textView_muito_abaixo_peso_title.text = "16 a 16,9 kg/m²"
                textView_muito_abaixo_peso_result.text = "Muito abaixo do peso"
                textView_muito_abaixo_peso_table.visibility = View.VISIBLE

                textView_abaixo_peso_title.text = "17 a 18,4 kg/m²"
                textView_abaixo_peso_result.text = "Abaixo do peso"
                textView_abaixo_peso_table.visibility = View.VISIBLE

                textView_peso_normal_title.text = "18,5 a 24,9 kg/m²"
                textView_peso_normal_result.text = "Peso normal"
                textView_peso_normal_table.visibility = View.VISIBLE

                textView_acima_peso_title.text = "25 a 29,9 kg/m²"
                textView_acima_peso_result.text = "Acima do peso"
                textView_acima_peso_table.visibility = View.VISIBLE

                textView_obesidadeI_title.text = "30 a 34,9 kg/m²"
                textView_obesidadeI_result.text = "Obesidade grau I"
                textView_obesidadeI_table.visibility = View.VISIBLE

                textView_obesidadeII_title.text = "35 a 40 kg/m²"
                textView_obesidadeII_result.text = "Obesidade grau II"
                textView_obesidadeII_table.visibility = View.VISIBLE

                textView_obesidadeIII_title.text = "Maior que 40 kg/m²"
                textView_obesidadeIII_result.text = "Obesidade grau III"
                textView_obesidadeIII_table.visibility = View.VISIBLE


                editTextNumber_altura.visibility = View.GONE
                editTextNumber_peso.visibility = View.GONE
                button_calcularIMC.visibility = View.GONE
                button_resetarIMC.visibility = View.VISIBLE
            }
        }


        button_resetarIMC.setOnClickListener {
            finish()
            startActivity(Intent(this, Imc_Calculator::class.java))

        }
    }
}


