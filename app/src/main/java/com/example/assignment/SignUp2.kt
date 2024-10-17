package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.databinding.ActivitySignUp2Binding
import android.util.Patterns


class SignUp2 : AppCompatActivity() {

    private lateinit var binding:ActivitySignUp2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Declaring the binding and setting the contentview to it
        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailAddres.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                binding.emailAddres.setText("")
            }
        }


        // Binding the createaccount button
        binding.createAccount.setOnClickListener {
            // Storing the values of the fields to variables and converting to string
            val emailAddress = binding.emailAddres.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            // Checking if the email field has a valid email format
            if (Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                // Checking if the password has between 8 and 15 characters
                if((password.length >= 8 && password.length <= 15)){
                    // Checking if the password and the confirmation of password matches
                    if (password == confirmPassword) {
                        // Saving the information
                        val sharedPreferences: SharedPreferences = getSharedPreferences("emailLoginData", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()

                        editor.putString("EMAILADDRESS",emailAddress)
                        editor.putString("PASSWORD",password)
                        editor.apply()

                        // Raising a toast telling the user that the account has been created successfully
                        Toast.makeText(this, "Account has been created.", Toast.LENGTH_SHORT).show()
                    } else {
                        // If the password and the confirmation does not match
                        // Warn the user
                        Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // If the password has less than 8 or more than 15 characters, warn the user
                    Toast.makeText(this, "Password must have between 8 and 15 characters.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // If the emailAddress field does not have a valid email format
                // Warn the user
                Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show()
            }
        }


        // Binding btnBackFromSignUp2 page to open main activity
        binding.btnBackFromSignUp2.setOnClickListener {
            val backToMainPageFromSignUp2 = Intent( this, MainActivity::class.java)
            startActivity(backToMainPageFromSignUp2)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}