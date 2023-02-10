package ru.aston.moroz_lesson2

import android.content.Intent
import android.os.Bundle
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

        savedInstanceState?.getString(SAVE_STATE_KEY)?.let { enteredValue ->
            with(binding) {
                textViewForSaveInstance.text = enteredValue
                inputText.setText(enteredValue)
            }
        }

        setValueToTextView()
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
//        with(binding) {
//            buttonNavigateToMainActivity.setOnClickListener {
//                val enteredValue = inputText.text.toString()
//                val intent = Intent(this@ru.aston.moroz_lesson2.SecondActivity, MainActivity::class.java)
//                    .apply {
//                        putExtra(NAVIGATE_KEY, enteredValue)
//                    }
//                startActivity(intent)
//            }
//        }

        // Solution using Activity Results API
        binding.buttonNavigateToMainActivity.setOnClickListener {
            val intent = Intent().apply {
                val passedValue = binding.inputText.text.toString()
                putExtra(SecondActivityContract.OUTPUT_DATA_KEY, passedValue)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setValueToTextView() {
        with(binding) {
            inputText.setOnClickListener {
                val enteredValue = inputText.text.toString()
                textViewForSaveInstance.text = enteredValue
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val enteredValue = binding.inputText.text.toString()
        outState.putString(SAVE_STATE_KEY, enteredValue)
    }
}