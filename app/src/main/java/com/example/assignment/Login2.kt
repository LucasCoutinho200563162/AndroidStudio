package com.example.assignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.databinding.ActivityLogin2Binding
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.example.assignment.databinding.ActivityMainBinding

class Login2 : AppCompatActivity() {
    private lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Declaring the binding and setting the contentview to it
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("emailLoginData", MODE_PRIVATE)
        val usernameStored = sharedPreferences.getString("EMAILADDRESS", null)
        val passwordStored = sharedPreferences.getString("PASSWORD", null)


        // Binding the usernamelogin field so when it is in focus it will go blank
        binding.usernameLogin.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.usernameLogin.setText("")
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.usernameLogin.text.toString()
            val password = binding.passwordLogin.text.toString()

            if ((username == usernameStored) && (password == passwordStored)) {
                val url = "https://github.com/"
                val openGitHub = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(openGitHub)
            } else {
                Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnBackFromLogin.setOnClickListener {
            val backToMainFromLogin = Intent(this, ActivityMainBinding::class.java )
            startActivity(backToMainFromLogin)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}