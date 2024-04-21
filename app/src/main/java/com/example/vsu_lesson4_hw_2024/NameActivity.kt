package com.example.vsu_lesson4_hw_2024

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.vsu_lesson4_hw_2024.databinding.ActivityNameBinding

private const val EXTRA_USER_RESULT = "EXTRA_USER_RESULT"
private const val EXTRA_BUNDLE_NAME = "EXTRA_BUNDLE_NAME"

class NameActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNameBinding
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputNameEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name = s.toString()
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
            val resultIntent = Intent(this, SurnameActivity::class.java).apply {
                val bundle = bundleOf(
                    EXTRA_BUNDLE_NAME to name
                )
                putExtra(EXTRA_USER_RESULT, bundle)
            }
            setResult(RESULT_OK, resultIntent)
            startActivity(resultIntent)
        }
    }
}