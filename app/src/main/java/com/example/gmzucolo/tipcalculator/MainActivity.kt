package com.example.gmzucolo.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gmzucolo.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        binding.calculateButton.setOnClickListener {
            val editText = binding.costOfService.text
            if (editText.isNotEmpty()) {
                calculateTip()
            } else {
                Toast.makeText(this, "insert tip", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun calculateTip() {
        val costOfService = binding.costOfService.text.toString().toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * costOfService
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
