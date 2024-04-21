package com.example.vsu_lesson4_hw_2024

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vsu_lesson4_hw_2024.databinding.ActivityMainBinding

private const val EXTRA_USER_RESULT = "EXTRA_USER_RESULT"
private const val EXTRA_BUNDLE_NAME = "EXTRA_BUNDLE_NAME"
private const val EXTRA_BUNDLE_SURNAME = "EXTRA_BUNDLE_SURNAME"
private const val EXTRA_BUNDLE_AGE = "EXTRA_BUNDLE_AGE"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val receivedData = it.getBundle(EXTRA_USER_RESULT)
            val name = receivedData?.getString(EXTRA_BUNDLE_NAME)
            val surname = receivedData?.getString(EXTRA_BUNDLE_SURNAME)
            val age = receivedData?.getString(EXTRA_BUNDLE_AGE)
            val user = User(name, surname, age)
            Toast.makeText(
                this,
                "Добро пожаловать, ${user.name} ${user.surname}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.beginRegistrationButton.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java).apply {
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }
}