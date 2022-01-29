package com.example.multicalculator

import android.os.Bundle
import android.os.Parcel
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder //https://uware.com.br/como-criar-uma-calculadora-em-kotlin-com-viewmodel/




class Calculator() : AppCompatActivity() {
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var textView_result = "0"
        var verificadorParaCalculo = false
        var segundoVerificadorParaCalculo = true
        var verificadorDePonto = true

        fun atualizarTela() {
            textView_resultado.text = textView_result.replace(".",",",false)
        }

        fun teclarNumero(numero: String) {
            if (textView_result.length == 1 && textView_result == "0") {
                textView_result = ""
            }
            textView_result += numero
            verificadorParaCalculo = true
            segundoVerificadorParaCalculo = true

            atualizarTela()
        }

        fun apagarComBackSpace() {
            if (textView_result.endsWith(".") == true) {
                verificadorDePonto = true
            }
            if (textView_result.startsWith("(") && textView_result.endsWith(")")) {
                textView_result = textView_result.substring(1, textView_result.length)
            }
            textView_result = textView_result.substring(0, textView_result.length - 1)
            if (textView_result.length == 0) {
                textView_result = "0"
                verificadorDePonto = true
            }

            if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith("/") || textView_result.endsWith("*")) {
                verificadorParaCalculo = false
                segundoVerificadorParaCalculo = false

            } else {
                verificadorParaCalculo = true
                segundoVerificadorParaCalculo = true
            }
            if (textView_result.length == 1 && textView_result == "0") {
                verificadorDePonto = true
            }
            atualizarTela()
        }

        fun colocarPonto() {
            if (verificadorDePonto == true) {
                if (textView_result.endsWith("*") || textView_result.endsWith("-") || textView_result.endsWith("+") || textView_result.endsWith(
                        "/"
                    )
                ) {
                    textView_result += "0."
                    verificadorDePonto = false
                    atualizarTela()
                } else {
                    textView_result += "."
                    verificadorDePonto = false
                    atualizarTela()
                }
            }
        }

        button_00.setOnClickListener { teclarNumero("0") }
        button_01.setOnClickListener { teclarNumero("1") }
        button_02.setOnClickListener { teclarNumero("2") }
        button_03.setOnClickListener { teclarNumero("3") }
        button_04.setOnClickListener { teclarNumero("4") }
        button_05.setOnClickListener { teclarNumero("5") }
        button_06.setOnClickListener { teclarNumero("6") }
        button_07.setOnClickListener { teclarNumero("7") }
        button_08.setOnClickListener { teclarNumero("8") }
        button_09.setOnClickListener { teclarNumero("9") }
        button_apagar.setOnClickListener { apagarComBackSpace() }
        button_virgula.setOnClickListener { colocarPonto() }


        button_soma.setOnClickListener {
            if (verificadorParaCalculo == true && segundoVerificadorParaCalculo == true) {
                textView_result += "+"
                atualizarTela()
                verificadorParaCalculo = false
                verificadorDePonto = true
            }
        }
            button_subtracao.setOnClickListener {
                if (textView_result.length == 1 && textView_result == "0") {
                    textView_result = "";
                }
                if (segundoVerificadorParaCalculo == true) {
                    textView_result += "-";
                    atualizarTela()
                    verificadorParaCalculo = false;
                    segundoVerificadorParaCalculo = false;
                    verificadorDePonto = true;
                }
            }
            button_divisao.setOnClickListener {
                if (verificadorParaCalculo == true && segundoVerificadorParaCalculo == true) {
                    textView_result += "/";
                    atualizarTela()
                    verificadorParaCalculo = false;
                    verificadorDePonto = true;
                }
            }
            button_multiplicacao.setOnClickListener {
                if (verificadorParaCalculo == true && segundoVerificadorParaCalculo == true) {
                    textView_result += "*"
                    atualizarTela()
                    verificadorParaCalculo = false
                    verificadorDePonto = true
                }
            }

        button_igual.setOnClickListener{

            textView_result.replace(",",".",false)

            if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith("/") || textView_result.endsWith(
                    "*"
                )
            ) {
                apagarComBackSpace()
                verificadorParaCalculo = true
                segundoVerificadorParaCalculo = true
            }

            if (textView_result.endsWith(".")) {
                apagarComBackSpace()
                verificadorDePonto = true
            }

            val calc = ExpressionBuilder( textView_result).build()
            textView_result = calc.evaluate().toString()

            atualizarTela()
        }
        button_C.setOnClickListener {
            textView_result = "0"
            atualizarTela()
            verificadorParaCalculo = false
            segundoVerificadorParaCalculo = true
            verificadorDePonto = true
        }
        button_mais_menos.setOnClickListener {
            if(textView_result.length == 1 && textView_result == "0"){

            }else{

                for(i in 0..1) {
                    if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith(
                            "/"
                        ) || textView_result.endsWith("*")
                    ) {
                        apagarComBackSpace()
                        verificadorParaCalculo = false;
                        segundoVerificadorParaCalculo = false;
                    } else {
                        verificadorParaCalculo = true;
                        segundoVerificadorParaCalculo = true;
                    }

                }

                textView_result = "($textView_result)*-1";
                atualizarTela()
            }
        }
        button_CE.setOnClickListener{
            var teste = true

            if (textView_result.contains("+") || textView_result.contains("-") || textView_result.contains("/") || textView_result.contains("*")){

                if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith("/") || textView_result.endsWith("*")) {

                    apagarComBackSpace()
                }
                while (teste == true && textView_result.length > 0) {
                    if (textView_result.endsWith(".") === true) {
                        verificadorDePonto = true
                    }
                    if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith("/") || textView_result.endsWith("*")) {

                        apagarComBackSpace()
                        teste = false
                        if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith(
                                "/"
                            ) || textView_result.endsWith("*")
                        ) {
                            apagarComBackSpace()
                            if (textView_result.startsWith("(") && textView_result.endsWith(")")) {
                                //textoCalc = textoCalc.substring(1,textoCalc.length()-1);
                                apagarComBackSpace()
                            }
                        }
                    } else {
                        apagarComBackSpace()
                    }
                }
            } else {
                textView_result = "0"
                verificadorDePonto = true
            }

            if (textView_result.length === 0) {
                textView_result = "0"
                verificadorDePonto = true
            }
            if (textView_result.endsWith("+") || textView_result.endsWith("-") || textView_result.endsWith("/") || textView_result.endsWith(
                    "*"
                )
            ) {
                verificadorParaCalculo = false
                segundoVerificadorParaCalculo = false
            } else {
                verificadorParaCalculo = true
                segundoVerificadorParaCalculo = true
            }

            atualizarTela()
        }




        }
    }


