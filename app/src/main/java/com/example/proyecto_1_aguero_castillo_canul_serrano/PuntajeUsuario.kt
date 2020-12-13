package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.prueba
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_puntaje_usuario.*

class PuntajeUsuario : AppCompatActivity() {

    private var db_values:Database = Database();
    private lateinit var  nombreUsuario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntaje_usuario)

        val usuario= intent.getStringExtra("Usuario")

        Stetho.initializeWithDefaults(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            db_values.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){

        }).build()


        nombreUsuario= findViewById(R.id.userName)

        nombreUsuario.setText("Usuario: "+usuario)


        var usuarioDatos= db.userDao().getUserByName(usuario)

        var scorePerUser= db.ScorePerMatch().getScorePerUser(usuarioDatos.id_usuario)

        var dataScores= arrayListOf<String>()
        var dataDates= arrayListOf<String>()

        var long= scorePerUser.size-1

        for(i in 0..long){
            dataScores.add(scorePerUser[i].score_per_match.toString())
            dataDates.add(scorePerUser[i].date_match)
        }

        var recyclerViewScores= findViewById<RecyclerView>(R.id.rvScoresU).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeUsuario)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataScores)
        }

        var recyclerViewDates= findViewById<RecyclerView>(R.id.rvDates).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeUsuario)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataDates)
        }

    }
}