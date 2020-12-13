package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.score_per_match
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



        val score1= score_per_match(null, 1, 30, "13-12-2020")
        val score2= score_per_match(null, 1, 40, "12-12-2020")
        val score3= score_per_match(null, 1, 20, "16-12-2020")

        db.ScorePerMatch().insertUser(score1.id_usuario, score1.score_per_match, score1.date_match)
        db.ScorePerMatch().insertUser(score2.id_usuario, score2.score_per_match, score2.date_match)
        db.ScorePerMatch().insertUser(score3.id_usuario, score3.score_per_match, score3.date_match)






        nombreUsuario= findViewById(R.id.userName)

        nombreUsuario.setText("Usuario: "+usuario)


        var usuarioDatos= db.userDao().getUserByName(usuario)

        var scorePerUser= db.ScorePerMatch().getScorePerUser(usuarioDatos.id_usuario)

        var dataScores= arrayListOf<String>()
        var dataDates= arrayListOf<String>()

        var long= scorePerUser.size-1

        for(i in 0..long){
            dataScores.add(scorePerUser[i].score_per_match.toString())
            dataDates.add(scorePerUser[i].date_match.toString())
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

        btnFechaOrder.setOnClickListener {

        }
        btnPuntajeOrder.setOnClickListener {
            var usuarioDatosDesc= db.userDao().getUserByName(usuario)

            var scorePerUserDesc= db.ScorePerMatch().getScorePerUserDesc(usuarioDatos.id_usuario)

            var dataScoresDesc= arrayListOf<String>()
            var dataDatesDesc= arrayListOf<String>()

            var longDesc= scorePerUserDesc.size-1

            for(i in 0..longDesc){
                dataScoresDesc.add(scorePerUserDesc[i].score_per_match.toString())
                dataDatesDesc.add(scorePerUserDesc[i].date_match.toString())
            }

            recyclerViewDates.adapter= MyAdapter(dataDatesDesc)
            recyclerViewScores.adapter= MyAdapter(dataScoresDesc)


        }

    }


}