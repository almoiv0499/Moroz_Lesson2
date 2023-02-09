package ru.aston.moroz_lesson2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.aston.moroz_lesson2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    companion object {
        private const val SAVE_STATE_KEY = "save_state_key"
    }

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        binding.buttonNavigateToMainActivity.setOnClickListener {

        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val valueFromEditText = binding.inputText.text.toString()
        outState.putString(SAVE_STATE_KEY, valueFromEditText)
    }
}