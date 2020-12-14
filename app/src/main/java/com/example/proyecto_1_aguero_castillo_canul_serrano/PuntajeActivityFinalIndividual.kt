package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase

class PuntajeActivityFinalIndividual : AppCompatActivity() {
    private var db_values:Database = Database();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntaje_final_individual2)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            db_values.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){

        }).build()


        var usuarios = db.ScorePerMatch().getFiveScores()


        var dataScores= arrayListOf<String>()
        var dataDates= arrayListOf<String>()
        var dataHints= arrayListOf<String>()

        var long= usuarios.size-1


        for(i in 0..long){
            dataScores.add(usuarios[i].score_per_match.toString())
            dataHints.add(usuarios[i].uso_pista.toString())
            dataDates.add(usuarios[i].date_match)
        }



        var recyclerViewScores = findViewById<RecyclerView>(R.id.rvScoresF).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeActivityFinalIndividual)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataScores)
        }



        var recyclerViewDates= findViewById<RecyclerView>(R.id.rvDatesF).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeActivityFinalIndividual)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataDates)
        }

        var recyclerViewHints= findViewById<RecyclerView>(R.id.rvPistasF).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeActivityFinalIndividual)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataHints)
        }
    }
}