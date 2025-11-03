package com.example.appnhapdulieu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.appnhapdulieu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCalendarToggle()
        setupDateSelection()
        setupRegistration()
    }

    private fun setupCalendarToggle() {
        binding.btnSelectDate.setOnClickListener {
            if (binding.datePicker.visibility == View.GONE) {
                binding.datePicker.visibility = View.VISIBLE
                binding.root.post {
                    binding.root.smoothScrollTo(0, binding.datePicker.top)
                }
            } else {
                binding.datePicker.visibility = View.GONE
            }
        }
    }

    private fun setupDateSelection() {
        binding.datePicker.init(
            binding.datePicker.year,
            binding.datePicker.month,
            binding.datePicker.dayOfMonth
        ) { view, year, monthOfYear, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            binding.etBirthday.setText(selectedDate)
            binding.datePicker.visibility = View.GONE
        }
    }

    private fun setupRegistration() {
        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        resetFieldBackgrounds()

        val errorBgColor = ContextCompat.getColor(this, R.color.error_input_bg)
        val errorTextColor = ContextCompat.getColor(this, R.color.error_text_color)

        if (binding.etFirstName.text.isBlank()) {
            binding.etFirstName.setBackgroundColor(errorBgColor)
            isValid = false
        }

        if (binding.etLastName.text.isBlank()) {
            binding.etLastName.setBackgroundColor(errorBgColor)
            isValid = false
        }

        if (binding.rgGender.checkedRadioButtonId == -1) {
            binding.rbMale.setTextColor(errorTextColor)
            binding.rbFemale.setTextColor(errorTextColor)
            isValid = false
        }

        if (binding.etBirthday.text.isBlank()) {
            binding.etBirthday.setBackgroundColor(errorBgColor)
            isValid = false
        }

        if (binding.etAddress.text.isBlank()) {
            binding.etAddress.setBackgroundColor(errorBgColor)
            isValid = false
        }

        if (binding.etEmail.text.isBlank()) {
            binding.etEmail.setBackgroundColor(errorBgColor)
            isValid = false
        }

        if (!binding.cbTerms.isChecked) {
            binding.cbTerms.setTextColor(errorTextColor)
            isValid = false
        }

        return isValid
    }

    private fun resetFieldBackgrounds() {
        val defaultBgColor = ContextCompat.getColor(this, R.color.default_input_bg)
        val defaultTextColor = ContextCompat.getColor(this, android.R.color.black)

        binding.etFirstName.setBackgroundColor(defaultBgColor)
        binding.etLastName.setBackgroundColor(defaultBgColor)
        binding.etBirthday.setBackgroundColor(defaultBgColor)
        binding.etAddress.setBackgroundColor(defaultBgColor)
        binding.etEmail.setBackgroundColor(defaultBgColor)

        binding.rbMale.setTextColor(defaultTextColor)
        binding.rbFemale.setTextColor(defaultTextColor)
        binding.cbTerms.setTextColor(defaultTextColor)
    }
}