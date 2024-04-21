package com.example.vsu_lesson4_hw_2024

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.vsu_lesson4_hw_2024.databinding.ActivityNameBinding

private const val EXTRA_USER_RESULT = "EXTRA_USER_RESULT"
private const val EXTRA_BUNDLE_NAME = "EXTRA_BUNDLE_NAME"
private const val EXTRA_BUNDLE_SURNAME = "EXTRA_BUNDLE_SURNAME"

class NameActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNameBinding
    private var surname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultListenerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val receivedData = result.data?.extras?.getBundle(EXTRA_USER_RESULT)
                surname = receivedData?.getString(EXTRA_BUNDLE_SURNAME).toString()
            }
        }

        binding.returnButton.setOnClickListener {
            val resultIntent = Intent().apply {
                val bundle = bundleOf(
                    EXTRA_BUNDLE_NAME to "pasha",
                    EXTRA_BUNDLE_SURNAME to surname
                )
                putExtra(EXTRA_USER_RESULT, bundle)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.goFurtherButton.setOnClickListener {
            val intent = Intent(this, SurnameActivity::class.java)
            resultListenerLauncher.launch(intent)
        }
    }
}