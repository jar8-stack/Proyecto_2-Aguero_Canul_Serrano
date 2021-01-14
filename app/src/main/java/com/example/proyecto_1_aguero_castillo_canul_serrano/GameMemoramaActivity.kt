package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GameMemoramaActivity : AppCompatActivity() {
    lateinit var jugador1Text: TextView
    lateinit var jugador2Text: TextView
    lateinit var refInviEnvi: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_memorama)


        var jugador1= intent.getStringExtra("jugador1")
        var jugador2= intent.getStringExtra("jugador2")

        jugador1Text= findViewById(R.id.jugador1)
        jugador2Text= findViewById(R.id.jugador2)

        jugador1Text.setText(jugador1)
        jugador2Text.setText(jugador2)

        refInviEnvi.child(jugador2).child("invitaciones_enviadas").setValue("")
        refInviEnvi.child(jugador1).child("invitaciones_recibidas").setValue("")





    }
}