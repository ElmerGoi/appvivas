package com.example.vistahorizontal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Variables de la calculadora
    private var oper: Int = 0
    private var numero1: Double = 0.0
    private lateinit var tv_num1: TextView
    private lateinit var tv_num2: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de la calculadora
        initializeCalculator()

        // Botón para el menú emergente
        val menuButton: Button = findViewById(R.id.menuButton)
        menuButton.setOnClickListener { showPopupMenu(menuButton) }
    }

    @SuppressLint("SetTextI18n")
    private fun initializeCalculator() {
        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)

        val btnIgual: Button = findViewById(R.id.btnIgual)
        val btnBorrar: Button = findViewById(R.id.btnBorrar)

        btnIgual.setOnClickListener {
            val num2 = tv_num2.text.toString().toDoubleOrNull() ?: 0.0
            val res = when (oper) {
                1 -> numero1 + num2
                2 -> numero1 - num2
                3 -> numero1 * num2
                4 -> if (num2 != 0.0) numero1 / num2 else Double.NaN
                else -> 0.0
            }
            tv_num2.text = res.toString()
            tv_num1.text = ""
        }

        btnBorrar.setOnClickListener {
            tv_num1.text = ""
            tv_num2.text = ""
            oper = 0
        }
    }

    @SuppressLint("SetTextI18n")
    fun clicNumero(view: View) {
        val num2 = tv_num2.text.toString()
        when (view.id) {
            R.id.btn0 -> tv_num2.text = num2 + "0"
            R.id.btn1 -> tv_num2.text = num2 + "1"
            R.id.btn2 -> tv_num2.text = num2 + "2"
            R.id.btn3 -> tv_num2.text = num2 + "3"
            R.id.btn4 -> tv_num2.text = num2 + "4"
            R.id.btn5 -> tv_num2.text = num2 + "5"
            R.id.btn6 -> tv_num2.text = num2 + "6"
            R.id.btn7 -> tv_num2.text = num2 + "7"
            R.id.btn8 -> tv_num2.text = num2 + "8"
            R.id.btn9 -> tv_num2.text = num2 + "9"
            R.id.btnPunto -> if (!num2.contains(".")) tv_num2.text = num2 + "."
        }
    }

    @SuppressLint("SetTextI18n")
    fun clicOperacion(view: View) {
        val num2 = tv_num2.text.toString().toDoubleOrNull() ?: return
        numero1 = num2
        tv_num2.text = ""
        when (view.id) {
            R.id.btnSumar -> {
                tv_num1.text = "$num2 +"
                oper = 1
            }
            R.id.btnRestar -> {
                tv_num1.text = "$num2 -"
                oper = 2
            }
            R.id.btnMult -> {
                tv_num1.text = "$num2 *"
                oper = 3
            }
            R.id.btnDividir -> {
                tv_num1.text = "$num2 /"
                oper = 4
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.menu_options, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_option1 -> {
                    // Cambiar a la vista de calculadora
                    setContentView(R.layout.calculadora)
                    // Inicializar nuevamente la calculadora después de cambiar la vista
                    initializeCalculator()
                    true
                }
                R.id.action_option2 -> {
                    // Cambiar a la vista de mapa
                    setContentView(R.layout.mapa)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
