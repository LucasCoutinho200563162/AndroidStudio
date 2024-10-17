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

        // Initiating and starting the binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Making the field empty on focus
        binding.userName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.userName.setText("")
            }
        }


        // Binding the create account button
        binding.createAccount.setOnClickListener {

            // Store the field values to variables
            val username = binding.userName.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            // Compare if the password matches the confirmation
            if (password == confirmPassword) {
                // If matches, then store the field values
                val sharedPreferences: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                editor.putString("USERNAME",username)
                editor.putString("PASSWORD",password)
                editor.apply()

                // Send a warning message to the user that the account has been created
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()


            } else {
                // If doesn't match, warn the user
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

        // Binding Back button to start the main activity
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