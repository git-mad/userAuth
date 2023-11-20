package com.example.testproject

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val signInButton = findViewById<Button>(R.id.signIn)
        val createAcc = findViewById<Button>(R.id.createAcc)
        auth = Firebase.auth
        createAcc.setOnClickListener {
            val myIntent = Intent(this, SignUp::class.java)
            startActivity(myIntent);
        }
        signInButton.setOnClickListener {
            val emailText = findViewById<EditText>(R.id.user)
            val passText = findViewById<EditText>(R.id.pass)

//            Sign In Button
            auth.signInWithEmailAndPassword(emailText.text.toString(), passText.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val myIntent = Intent(this, MainActivity::class.java)
                        startActivity(myIntent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
//                        updateUI(null)
                    }
                }

        }
    }
}