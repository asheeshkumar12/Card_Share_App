package com.example.cardshare


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.Key
import kotlin.math.sign


class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY1="com.example.cardshare.SignInActivity.name"
        const val KEY2="com.example.cardshare.SignInActivity.mail"
        const val KEY3="com.example.cardshare.SignInActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity_main)

         // extract uniqueid here
        val username=findViewById<EditText>(R.id.uid)
        val signinBtn=findViewById<Button>(R.id.btnSignIn)

        val newUser=findViewById<TextView>(R.id.txtLogin)
        newUser.setOnClickListener {
            val nintent=Intent(this,SignUpActivity::class.java)
            startActivity(nintent)
        }

        signinBtn.setOnClickListener {
            // take reference
            val uniqueId = username.text.toString()
            if (uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this,"please enter unique id",Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun readData(uniqueId: String) {
     databaseReference=FirebaseDatabase.getInstance().getReference("Users")
         databaseReference.child(uniqueId).get().addOnSuccessListener {

            // if user id exist check first
            if(it.exists()){
                // user unique id all ready exist
                val email=it.child("email").value
                val name=it.child("name").value
                val userid=it.child("user").value

                val intentWelcome=Intent(this,HomeActivity::class.java)
                intentWelcome.putExtra(KEY1,name.toString())
                intentWelcome.putExtra(KEY2,email.toString())
                intentWelcome.putExtra(KEY3,userid.toString())
                startActivity(intentWelcome)


            }else{
                // user not exist
                Toast.makeText(this,"Unique id not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failure",Toast.LENGTH_SHORT).show()
        }
    }
}