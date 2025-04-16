package com.example.proyectoprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class activityShow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val fab = findViewById<Button>(R.id.add)

        fab.setOnClickListener {
            val intent = Intent(this, addEvento::class.java)
            startActivity(intent)
        }
    }
}