package ru.aston.moroz_lesson2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aston.moroz_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToSecondFragment()
    }

    fun changeViewsLanguage() {
        binding.buttonEnglishLanguage.setOnClickListener {

        }
        binding.buttonRussianLanguage.setOnClickListener {

        }
    }

    private fun navigateToSecondFragment() {
        binding.buttonNavigateToSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}