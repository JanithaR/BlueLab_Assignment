package com.example.bluelab_assignment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var category: String = ""
    var unit1: String = ""
    var unit2: String = ""
    var unit1Value: Double = 0.0
    var unit2Value: Double = 0.0
    var input1HasFocus = true
    var input2HasFocus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        initCategoriesSpinner()
        initInputs()
    }

    private fun clearInput1() {
        if (editTextInput1.text.toString() != "") {
            editTextInput1.setText("")
        }
    }

    private fun clearInput2() {
        if (editTextInput2.text.toString() != "") {
            editTextInput2.setText("")
        }
    }

    private fun initInputs() {
        editTextInput1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val temp: Double

                if (!s.toString().isEmpty() && s.toString() != "-") {
                    temp = s.toString().toDouble()

                    if (temp != unit1Value) {
                        unit1Value = temp

                        val conversionResponse =
                            ConverterUtil.convert(
                                category = category,
                                fromUnit = unit1,
                                toUnit = unit2,
                                value = unit1Value
                            )

                        unit2Value = conversionResponse.toUnitValue

                        editTextInput2.setText(unit2Value.toString())

                        moveCursorToEnd(editTextInput2)
                    }
                } else {
                    clearInput2()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // nothing here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // nothing here
            }
        })

        editTextInput2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val temp: Double

                if (!s.toString().isEmpty() && s.toString() != "-") {
                    temp = s.toString().toDouble()

                    if (temp != unit2Value) {
                        unit2Value = temp

                        val conversionResponse =
                            ConverterUtil.convert(
                                category = category,
                                fromUnit = unit2,
                                toUnit = unit1,
                                value = unit2Value
                            )

                        unit1Value = conversionResponse.toUnitValue

                        editTextInput1.setText(unit1Value.toString())

                        moveCursorToEnd(editTextInput1)
                    }
                } else {
                    clearInput1()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // noting here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // nothing here
            }
        })

        editTextInput1.setOnFocusChangeListener { view, hasFocus ->
            run {
                if (hasFocus) {
                    input1HasFocus = hasFocus

                    moveCursorToEnd(view as EditText)
                }
            }
        }

        editTextInput2.setOnFocusChangeListener { view, hasFocus ->
            run {
                if (hasFocus) {
                    input2HasFocus = hasFocus

                    moveCursorToEnd(view as EditText)
                }
            }
        }
    }

    private fun moveCursorToEnd(view: EditText) {
        view.setSelection(view.text.length)
    }

    private fun initCategoriesSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategory.adapter = adapter
        }

        spinnerCategory.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // nothing here
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            spinnerCategory.id -> {
                category = parent.getItemAtPosition(position).toString()

                initUnitSpinners(category)
            }
            spinnerUnit1.id -> {
                unit1 = parent.getItemAtPosition(position).toString()
            }
            spinnerUnit2.id -> {
                unit2 = parent.getItemAtPosition(position).toString()
            }
        }

        val conversionResponse =
            ConverterUtil.convert(category = category, fromUnit = unit1, toUnit = unit2, value = unit1Value)

        if (conversionResponse.fromUnitValue != unit1Value) {
            editTextInput1.setText(conversionResponse.fromUnitValue.toString())

            moveCursorToEnd(editTextInput1)
        }

        if (conversionResponse.toUnitValue != unit1Value) {
            editTextInput2.setText(conversionResponse.toUnitValue.toString())

            moveCursorToEnd(editTextInput2)
        }
    }

    private fun initUnitSpinners(category: String) {
        val textArrayResId: Int = when (category) {
            "Temperature" -> {
                R.array.temperature_units_array
            }
            "Volume" -> {
                R.array.volume_units_array
            }
            else -> throw Error("Unable to initialize unit spinners. Relevant units not found.")
        }

        ArrayAdapter.createFromResource(
            this,
            textArrayResId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerUnit1.adapter = adapter
            spinnerUnit2.adapter = adapter
        }

        spinnerUnit1.onItemSelectedListener = this
        spinnerUnit2.onItemSelectedListener = this
    }
}
