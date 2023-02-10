package ru.aston.moroz_lesson2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aston.moroz_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val STORE_KEY = "store_key"
        private const val STORAGE_FILE = "storage_file"
    }

    private lateinit var binding: ActivityMainBinding

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        getSharedPreferences(STORAGE_FILE, Context.MODE_PRIVATE)
    }

    private val editor by lazy(LazyThreadSafetyMode.NONE) {
        sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeAndShowGotValue()
        navigateToSecondFragment()
    }

    private fun storeAndShowGotValue() {
        val receivedValue = intent.getStringExtra(SecondActivity.NAVIGATE_KEY)
        if (receivedValue != null && receivedValue.isNotEmpty()) {
            editor.putString(STORE_KEY, receivedValue).apply()
            binding.textViewMainActivity.text = receivedValue
        } else {
            binding.textViewMainActivity.text =
                sharedPreferences.getString(STORE_KEY, getString(R.string.hello_main_activity))
        }
    }

    private fun navigateToSecondFragment() {
        binding.buttonNavigateToSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editor.remove(STORE_KEY).apply()
    }
}