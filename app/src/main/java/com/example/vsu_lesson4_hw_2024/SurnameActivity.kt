package com.example.vsu_lesson4_hw_2024

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.vsu_lesson4_hw_2024.databinding.ActivitySurnameBinding

private const val EXTRA_USER_RESULT = "EXTRA_USER_RESULT"
private const val EXTRA_BUNDLE_NAME = "EXTRA_BUNDLE_NAME"
private const val EXTRA_BUNDLE_SURNAME = "EXTRA_BUNDLE_SURNAME"
private const val EXTRA_BUNDLE_AGE = "EXTRA_BUNDLE_AGE"

class SurnameActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurnameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySurnameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultListenerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val receivedData = result.data?.extras?.getBundle(EXTRA_USER_RESULT)
                val age = receivedData?.getString(EXTRA_BUNDLE_AGE)
            }
        }

        binding.returnButton.setOnClickListener {
            val resultIntent = Intent().apply {
                val bundle = bundleOf(
                    EXTRA_BUNDLE_SURNAME to "ivanov"
                )
                putExtra(EXTRA_USER_RESULT, bundle)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.goToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        binding.goFurtherButton.setOnClickListener {
            val intent = Intent(this, SurnameActivity::class.java)
            resultListenerLauncher.launch(intent)
        }
    }
}