package com.example.cardshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignUp=findViewById<Button>(R.id.btnSign)
        val useName=findViewById<EditText>(R.id.nameid)
        val emSign=findViewById<EditText>(R.id.emailSign)
        val uid=findViewById<EditText>(R.id.userid)
        val passwordEditText = findViewById<EditText>(R.id.passFrist)
        val confirmPasswordEditText = findViewById<EditText>(R.id.passConf)

        btnSignUp.setOnClickListener {
            // intent move next activity
            val intent= Intent(this,HomeActivity::class.java)
            val etEmail=emSign.text.toString()
            val etName=useName.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val userid=uid.text.toString()
            val user=User(etEmail,etName,userid,password)
            database=FirebaseDatabase.getInstance().getReference("Users")
            if(password==confirmPassword){
                 database.child(userid).setValue(user).addOnSuccessListener {
                     emSign.text?.clear()
                     uid.text?.clear()
                     useName.text?.clear()
                     passwordEditText.text?.clear()
                     confirmPasswordEditText.text?.clear()
                     Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
                     startActivity(intent)
                 }

            }
            else{
                Toast.makeText(this,"Password should not match !",Toast.LENGTH_SHORT).show()
            }

        }
    }
}