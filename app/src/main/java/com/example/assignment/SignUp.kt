package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.userName.setText("")
            }
        }

        binding.createAccount.setOnClickListener {
            var username = binding.userName.text.toString()
            var password = binding.password.text.toString()
            var confirmPassword = binding.confirmPassword.text.toString()

            if (password == confirmPassword) {
                val sharedPreferences: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                editor.putString("USERNAME",username)
                editor.putString("PASSWORD",password)
                editor.apply()


                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}