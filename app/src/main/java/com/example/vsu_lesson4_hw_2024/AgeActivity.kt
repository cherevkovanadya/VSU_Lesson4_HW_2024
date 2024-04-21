package com.example.vsu_lesson4_hw_2024

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.vsu_lesson4_hw_2024.databinding.ActivityAgeBinding

private const val EXTRA_USER_RESULT = "EXTRA_USER_RESULT"
private const val EXTRA_BUNDLE_NAME = "EXTRA_BUNDLE_NAME"
private const val EXTRA_BUNDLE_SURNAME = "EXTRA_BUNDLE_SURNAME"
private const val EXTRA_BUNDLE_AGE = "EXTRA_BUNDLE_AGE"

class AgeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAgeBinding
    private var name = ""
    private var surname = ""
    private var age = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val receivedData = it.getBundle(EXTRA_USER_RESULT)
            name = receivedData?.getString(EXTRA_BUNDLE_NAME).toString()
            surname = receivedData?.getString(EXTRA_BUNDLE_SURNAME).toString()
        }

        binding.inputNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                age = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.returnButton.setOnClickListener {
            finish()
        }

        binding.goToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                val bundle = bundleOf(
                    EXTRA_BUNDLE_NAME to name,
                    EXTRA_BUNDLE_SURNAME to surname,
                    EXTRA_BUNDLE_AGE to age
                )
                putExtra(EXTRA_USER_RESULT, bundle)
                //setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            setResult(RESULT_OK, intent)
            startActivity(intent)
            finishAffinity()
        }
    }
}