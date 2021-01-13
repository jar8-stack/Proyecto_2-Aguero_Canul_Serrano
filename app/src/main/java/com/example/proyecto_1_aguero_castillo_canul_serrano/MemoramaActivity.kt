package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_1_aguero_castillo_canul_serrano.prueba
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_memorama.*

class MemoramaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama)

        var userName= intent.getStringExtra("userName")

        //Hola mundo
        val database = Firebase.database
        val myRef = database.getReference()
        var usuarios= database.getReference("users_waiting")

        //myRef.setValue("Hello "+userName.toString())
        myRef.child("Usuarios").child(userName).child("buscando_partida").setValue("si")
        myRef.child("Usuarios").child(userName).child("puntaje_por_partida").setValue(0)

    }
}

@IgnoreExtraProperties
data class User(
    var buscando_partida: String? = "",
    var puntaje_por_partida: Int? = 0
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "confirm" to buscando_partida,
            "points" to puntaje_por_partida
        )
    }
}