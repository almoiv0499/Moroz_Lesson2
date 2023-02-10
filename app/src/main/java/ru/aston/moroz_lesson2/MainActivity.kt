package ru.aston.moroz_lesson2

import android.content.Context
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

    // Activity Results API
    private val receivedValueLauncher =
        registerForActivityResult(SecondActivityContract()) { receivedValue ->
            if (receivedValue != null && receivedValue.isNotEmpty()) {
                editor.putString(STORE_KEY, receivedValue).apply()
                binding.textViewMainActivity.text = receivedValue
            } else {
                binding.textViewMainActivity.text =
                    sharedPreferences.getString(STORE_KEY, getString(R.string.hello_main_activity))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToSecondFragment()
    }

    private fun navigateToSecondFragment() {
        binding.buttonNavigateToSecondActivity.setOnClickListener {

            // Activity Results API (launch)
            receivedValueLauncher.launch(binding.textViewMainActivity.text.toString())

            // Modern solution
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editor.remove(STORE_KEY).apply()
    }
}