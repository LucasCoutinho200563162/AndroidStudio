package com.example.assignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        // Declaring the binding and setting the contentview to it
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Binding the button login to go to login class
        binding.btnToLogin.setOnClickListener {
            val switchToLogin = Intent(this, Login::class.java)
            startActivity(switchToLogin)
        }

        // Binding the button login to go to signup class
        binding.btnToSignUp.setOnClickListener {
            val switchToSignUp = Intent(this, SignUp::class.java)
            startActivity(switchToSignUp)
        }

        // Binding the button login2 to go to login2 class
        binding.btnToLogin2.setOnClickListener {
            val switchToLogin2 = Intent (this, Login2::class.java)
            startActivity(switchToLogin2)
        }


        // Binding the button sign up to go to sign up2 class
        binding.btnToSignUp2.setOnClickListener {
            val switchToSignUp2 = Intent(this, SignUp2::class.java)
            startActivity(switchToSignUp2)
        }


        // Binding btn Updates
        binding.btnUpdates.setOnClickListener {
            // declare val with url to redirect
            val url = "https://github.blog/changelog/"
            // Declare the intent and parsing the url as uri
            val updateIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(updateIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}