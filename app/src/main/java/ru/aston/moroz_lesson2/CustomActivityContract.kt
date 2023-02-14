package ru.aston.moroz_lesson2

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class CustomActivityContract : ActivityResultContract<String, String?>() {

    companion object {
        const val OUTPUT_DATA_KEY = "output_data_key"
        const val INPUT_DATA_KEY = "input_data_key"
    }

    override fun createIntent(context: Context, input: String): Intent {
        return Intent(context, SecondActivity::class.java).apply {
            putExtra(INPUT_DATA_KEY, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when (resultCode) {
        Activity.RESULT_OK -> intent?.getStringExtra(OUTPUT_DATA_KEY)
        else -> null
    }
}