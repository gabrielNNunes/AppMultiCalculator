package com.example.multicalculator

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_imc_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder


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

            if(altura.isNotEmpty() && peso.isNotEmpty()) {
                val alturaNumber = altura.toDouble()
                val pesoNumber = peso.toDouble()

                if ( alturaNumber.isNaN() || alturaNumber < 1 || pesoNumber < 1 || pesoNumber.isNaN()) {
                    Toast.makeText(
                        this,
                        getString(R.string.info_error),
                        Toast.LENGTH_LONG
                    ).show()
                } else {


                    val result: Double = pesoNumber / (alturaNumber * alturaNumber)

                    val setColorIMC = Color.LTGRAY
                    when (result) {
                        in 16.0..16.9 -> textView_muito_abaixo_peso_table.setBackgroundColor(
                            setColorIMC
                        )
                        in 17.0..18.4 -> textView_abaixo_peso_table.setBackgroundColor(setColorIMC)
                        in 18.5..24.9 -> textView_peso_normal_table.setBackgroundColor(setColorIMC)
                        in 25.0..29.9 -> textView_acima_peso_table.setBackgroundColor(setColorIMC)
                        in 30.0..34.9 -> textView_obesidadeI_table.setBackgroundColor(setColorIMC)
                        in 35.0..40.0 -> textView_obesidadeII_table.setBackgroundColor(setColorIMC)
                        else -> {
                            if (result > 40.0) {
                                textView_obesidadeIII_table.setBackgroundColor(Color.LTGRAY)
                            }
                        }
                    }

                    textView_resultScreen.text = String.format("%.2f kg/m²", result)

                    textView_muito_abaixo_peso_table.visibility = View.VISIBLE
                    textView_abaixo_peso_table.visibility = View.VISIBLE
                    textView_peso_normal_table.visibility = View.VISIBLE
                    textView_acima_peso_table.visibility = View.VISIBLE
                    textView_obesidadeI_table.visibility = View.VISIBLE
                    textView_obesidadeII_table.visibility = View.VISIBLE
                    textView_obesidadeIII_table.visibility = View.VISIBLE


                    editTextNumber_altura.visibility = View.GONE
                    editTextNumber_peso.visibility = View.GONE
                    button_calcularIMC.visibility = View.GONE
                    button_resetarIMC.visibility = View.VISIBLE
                    button_infoIMC.visibility = View.VISIBLE
                }
            }else{
                Toast.makeText(
                    this,
                    getString(R.string.info_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        button_resetarIMC.setOnClickListener {
            finish()
            startActivity(Intent(this, Imc_Calculator::class.java))

        }

        button_infoIMC.setOnClickListener {
            val url = "https://www.uol.com.br/vivabem/faq/imc-como-calcular-tabela-dicas-como-melhorar-e-mais.htm"
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.open_link))
            builder.setMessage(getString(R.string.link_opening_navegation)+"\n\n$url")
            builder.setPositiveButton(getString(R.string.yes)
            ) { dialog, id ->
                Toast.makeText(this, getString(R.string.opening), Toast.LENGTH_SHORT).show()

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
                .setNegativeButton(getString(R.string.no)
                ) { dialog, id ->
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
                }
            builder.create().show()

        }


    }
}










