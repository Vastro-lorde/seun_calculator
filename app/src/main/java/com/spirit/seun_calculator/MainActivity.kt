package com.spirit.seun_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Display texts
        val resultDisplay = findViewById<TextView>(R.id.result_text)
        val expressionDisplay = findViewById<TextView>(R.id.expression_text)

        // action buttons
        val clearBtn = findViewById<Button>(R.id.clear_btn)
        val clearEntryBtn = findViewById<Button>(R.id.clear_entry_btn)
        val delBtn = findViewById<Button>(R.id.delete_btn)
        val equalsBtn = findViewById<TextView>(R.id.equals_btn)

        // operator buttons
        val decimalBtn = findViewById<Button>(R.id.btn_decimal)
        val percentBtn = findViewById<Button>(R.id.percentage_btn)
        val plusBtn = findViewById<Button>(R.id.plus_btn)
        val minusBtn = findViewById<Button>(R.id.minus_btn)
        val multiplyBtn = findViewById<Button>(R.id.multiplication_btn)
        val divideBtn = findViewById<Button>(R.id.division_btn)

        // number buttons
        val btn0 = findViewById<Button>(R.id.btn_0)
        val btn1 = findViewById<Button>(R.id.btn_1)
        val btn2 = findViewById<Button>(R.id.btn_2)
        val btn3 = findViewById<Button>(R.id.btn_3)
        val btn4 = findViewById<Button>(R.id.btn_4)
        val btn5 = findViewById<Button>(R.id.btn_5)
        val btn6 = findViewById<Button>(R.id.btn_6)
        val btn7 = findViewById<Button>(R.id.btn_7)
        val btn8 = findViewById<Button>(R.id.btn_8)
        val btn9 = findViewById<Button>(R.id.btn_9)

        // List of buttons that should add to the expression display
        val buttons = listOf(
            decimalBtn, percentBtn, plusBtn, minusBtn, multiplyBtn, divideBtn,
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9
        )

        // Set up click listeners for these buttons`
        buttons.forEach { button ->
            button.setOnClickListener {
                    val buttonText = button.text.toString()
                    val resultDisplayText = resultDisplay.text.toString()
                    if (resultDisplayText == getString(R.string.default_value)) {
                        resultDisplay.text = ""
                    }
                    addToDisplay(resultDisplay, buttonText)
                    if (!buttonText.isDigitsOnly() && buttonText != "." && buttonText != "%") {
                        addToDisplay(expressionDisplay, "$resultDisplayText$buttonText")
                        resultDisplay.text = getString(R.string.default_value)
                    }

            }
        }

        //setting up clear button on click listener
        clearBtn.setOnClickListener {
            expressionDisplay.text = ""
            resultDisplay.text = getString(R.string.default_value)
        }

        clearEntryBtn.setOnClickListener {
            resultDisplay.text = getString(R.string.default_value)
        }

        delBtn.setOnClickListener {
            val currentText = resultDisplay.text.toString()
            if (currentText.isNotEmpty()) {
                val newText = currentText.substring(0, currentText.length - 1)
                if (newText.isEmpty()) {
                    resultDisplay.text = getString(R.string.default_value)
                } else {
                    resultDisplay.text = newText
                }
            }
        }
        equalsBtn.setOnClickListener {
            val expression = expressionDisplay.text.toString()
            val result = evaluateExpression(expression)
            resultDisplay.text = result.toString()
        }

    }


    private fun addToDisplay(displayText: TextView, text: String) {
        val currentText = displayText.text.toString()
        val newText = "$currentText$text"
        displayText.text = newText
    }

    private fun evaluateExpression(expression: String) {
        println(expression)
    }
}