package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtDisplay: TextView
    private var operand1 = 0
    private var operand2 = 0
    private var operator = ""
    private var newOperand = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDisplay = findViewById(R.id.txtDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                val text = (it as Button).text.toString()
                if (newOperand || txtDisplay.text.toString() == "0") {
                    txtDisplay.text = text
                    newOperand = false
                } else {
                    txtDisplay.append(text)
                }
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOperator("/") }
        findViewById<Button>(R.id.btnEq).setOnClickListener { calculateResult() }

        findViewById<Button>(R.id.btnC).setOnClickListener {
            txtDisplay.text = "0"
            operand1 = 0
            operand2 = 0
            operator = ""
        }

        findViewById<Button>(R.id.btnCE).setOnClickListener {
            txtDisplay.text = "0"
        }

        findViewById<Button>(R.id.btnBS).setOnClickListener {
            val text = txtDisplay.text.toString()
            if (text.length > 1) txtDisplay.text = text.dropLast(1)
            else txtDisplay.text = "0"
        }
    }

    private fun setOperator(op: String) {
        operand1 = txtDisplay.text.toString().toInt()
        operator = op
        newOperand = true
    }

    private fun calculateResult() {
        operand2 = txtDisplay.text.toString().toInt()
        val result = when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 != 0) operand1 / operand2 else 0
            else -> operand2
        }
        txtDisplay.text = result.toString()
        newOperand = true
    }
}
