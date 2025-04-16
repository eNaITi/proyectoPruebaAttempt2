package com.example.proyectoprueba

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var tfUser: TextInputEditText
    private lateinit var btnSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tfUser = findViewById(R.id.tfUser)
        btnSignIn = findViewById(R.id.btnSignIn)

        btnSignIn.setOnClickListener {
            Log.d("MainActivity", "Botón presionado")
            cambiarPantalla()
        }
    }

    private fun cambiarPantalla() {
        val nombre = tfUser.text.toString().trim()

        if (nombre.isNotEmpty()) {
            try {
                val intent = Intent(this, activityShow::class.java)
                intent.putExtra("usuario", nombre)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al cambiar de pantalla: ${e.message}")
                Toast.makeText(this, "Error al iniciar la siguiente pantalla", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No has ingresado tu nombre", Toast.LENGTH_SHORT).show()
        }
    }
}