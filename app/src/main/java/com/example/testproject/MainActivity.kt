package com.example.testproject

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val writeButton = findViewById<Button>(R.id.button)
        val readButton = findViewById<Button>(R.id.button3)
        val textView = findViewById<TextView>(R.id.textView)
        auth = Firebase.auth


        writeButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            // Create a new user with a first and last name
            val user = hashMapOf(
                "first" to editText.text.toString(),
                "data" to 1815,
            )

            // Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
        readButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            // Create a new user with a first and last name
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    textView.text = ""
                    for (document in result) {
                        textView.text = textView.text.toString() + "\n" + document.data.toString();
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }




    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            signed in code
//            reload()
        }
    }
}