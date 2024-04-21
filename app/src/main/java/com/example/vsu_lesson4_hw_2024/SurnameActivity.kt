package com.example.vsu_lesson4_hw_2024

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.vsu_lesson4_hw_2024.databinding.ActivitySurnameBinding

private const val EXTRA_USER_RESULT = "EXTRA_USER_RESULT"
private const val EXTRA_BUNDLE_NAME = "EXTRA_BUNDLE_NAME"
private const val EXTRA_BUNDLE_SURNAME = "EXTRA_BUNDLE_SURNAME"

class SurnameActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurnameBinding
    private var name = ""
    private var surname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySurnameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val receivedData = it.getBundle(EXTRA_USER_RESULT)
            name = receivedData?.getString(EXTRA_BUNDLE_NAME).toString()
        }

        binding.inputNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                surname = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.returnButton.setOnClickListener {
            finish()
        }

        binding.goToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finishAffinity()
        }

        binding.goFurtherButton.setOnClickListener {
            val resultIntent = Intent(this, AgeActivity::class.java).apply {
                val bundle = bundleOf(
                    EXTRA_BUNDLE_NAME to name,
                    EXTRA_BUNDLE_SURNAME to surname
                )
                putExtra(EXTRA_USER_RESULT, bundle)
            }
            setResult(RESULT_OK, resultIntent)
            startActivity(resultIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy surname", "used")
    }
}