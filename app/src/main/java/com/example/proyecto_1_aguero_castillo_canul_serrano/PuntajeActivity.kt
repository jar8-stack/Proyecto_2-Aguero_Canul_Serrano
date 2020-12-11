package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_puntaje.*

class PuntajeActivity : AppCompatActivity() {


    //Hola mundo
    private lateinit var primeroN: TextView
    private lateinit var primeroS: TextView

    private lateinit var segundoN: TextView
    private lateinit var segundoS: TextView

    private lateinit var terceroN: TextView
    private lateinit var terceroS: TextView

    private lateinit var cuartoN: TextView
    private lateinit var cuartoS: TextView

    private lateinit var quintoN: TextView
    private lateinit var quintoS: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntaje)

        primeroN= findViewById(R.id.primeroNombre)
        primeroS= findViewById(R.id.primeroScore)

        segundoN= findViewById(R.id.segundoNombre)
        segundoS= findViewById(R.id.segundoScore)

        terceroN= findViewById(R.id.terceroNombre)
        terceroS= findViewById(R.id.terceroScore)

        cuartoN= findViewById(R.id.cuartoNombre)
        cuartoS= findViewById(R.id.cuartoScore)

        quintoN= findViewById(R.id.quintoNombre)
        quintoS= findViewById(R.id.quintoScore)

        Stetho.initializeWithDefaults(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quizapp_1_7.db"
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){

        }).build()

        var usuarios = db.userDao().getScore()


        primeroN.setText(usuarios[0].score_usuario.toString())
        primeroS.setText(usuarios[0].nombre_usuario.toString())

        segundoN.setText(usuarios[1].score_usuario.toString())
        segundoS.setText(usuarios[1].nombre_usuario.toString())

        terceroN.setText(usuarios[2].score_usuario.toString())
        terceroS.setText(usuarios[2].nombre_usuario.toString())

        cuartoN.setText(usuarios[3].score_usuario.toString())
        cuartoS.setText(usuarios[3].nombre_usuario.toString())

        quintoN.setText(""+usuarios[4].score_usuario.toString())
        quintoS.setText(usuarios[4].nombre_usuario.toString())





    }
}