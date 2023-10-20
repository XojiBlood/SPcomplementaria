package com.example.spcomplementaria

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private val clave = "valor"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val SP = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val guardar = findViewById<Button>(R.id.btnGuardar)
        val buscar = findViewById<Button>(R.id.btnBuscar)
        val ruttext = findViewById<EditText>(R.id.EtextRut)
        val nombretext = findViewById<EditText>(R.id.EtextNombre)
        val correotext = findViewById<EditText>(R.id.EtextCorreo)


        guardar.setOnClickListener {
            val rut = ruttext.text.toString()
            val nombre = nombretext.text.toString()
            val correo = correotext.text.toString()

            val edit = SP.edit()
            edit.putString(rut, "$nombre|$correo")
            edit.apply()

            ruttext.setText("")
            nombretext.setText("")
            correotext.setText("")
        }

        buscar.setOnClickListener {
            val rut = ruttext.text.toString()
            val userdata = SP.getString(rut, null)

            if (userdata != null){
                val data =userdata.split("|")
                nombretext.setText(data[0])
                correotext.setText(data[1])
            } else {
                mostrarDialogoDeAlerta("Usuario no encontrado")
            }
        }
    }

    private fun mostrarDialogoDeAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta")
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }
}