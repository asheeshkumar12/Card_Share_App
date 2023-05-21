package com.example.cardshare


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val name=intent.getStringExtra(SignInActivity.KEY1)
        val email=intent.getStringExtra(SignInActivity.KEY2)
        val userid=intent.getStringExtra(SignInActivity.KEY3)

        val welcomeTxt=findViewById<TextView>(R.id.tvwelcome)
        val txtEmail=findViewById<TextView>(R.id.showMail)
        val unid=findViewById<TextView>(R.id.showId)

        welcomeTxt.text="Welcome  $name"
        txtEmail.text="Mail: $email"
        unid.text="User id : $userid"

    }
}