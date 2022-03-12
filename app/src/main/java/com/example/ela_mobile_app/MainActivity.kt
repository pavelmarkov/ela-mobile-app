package com.example.ela_mobile_app

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listView)
        val db = Firebase.firestore
        db.collection("words")
            .get()
            .addOnSuccessListener { result ->
                val wordsList = arrayOfNulls<String>(result.size())
                for ((i, document) in result.withIndex()) {
                    Log.d("FIREBASE", "${document.id} => ${document.data}")
                    val eng = document.data.getValue("word").toString()
                    val rus = document.data.getValue("rus").toString()
                    wordsList[i] = "$eng - ${if (rus.isNullOrEmpty()) "?" else "$rus"}"
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, wordsList)
                listView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("FIREBASE", "Error getting documents.", exception)
            }
    }
}