package com.example.ela_mobile_app

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = ""
        val db = Firebase.firestore
        db.collection("words")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("FIREBASE", "${document.id} => ${document.data}")
                    val word = document.data.getValue("word").toString()
                    textView.text = "${textView.text}\n$word"
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIREBASE", "Error getting documents.", exception)
            }
    }
}