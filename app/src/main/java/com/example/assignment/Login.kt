package com.example.assignment

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Changing the field value to empty when on focus for the first time
        binding.usernameLogin.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                binding.usernameLogin.setText("")
            }
        }


        // Binding the login button
        binding.btnLogin.setOnClickListener {
            // Getting the values saved locally
            val sharedPreferences: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
            val usernameStored = sharedPreferences.getString("USERNAME", null)
            val passwordStored = sharedPreferences.getString("PASSWORD", null)

            // Getting the values from the field
            var username = binding.usernameLogin.text.toString()
            var password = binding.passwordLogin.text.toString()


            // Compare if the username and password from field matches local data
            if ((username == usernameStored) && (password == passwordStored)) {
                // If so, tell the user that they logged in
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                // If not, warn about error
                Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_SHORT).show()
            }
        }


        // Finishing activity when pressing back
        binding.btnBackFromLogin.setOnClickListener {
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}