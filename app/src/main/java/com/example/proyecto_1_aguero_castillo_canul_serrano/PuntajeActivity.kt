package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_puntaje.*

class PuntajeActivity : AppCompatActivity() {

    private var db_values:Database = Database();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntaje)

        Stetho.initializeWithDefaults(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            db_values.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){

        }).build()

        var usuarios = db.userDao().getScore()


        var dataNames= arrayListOf<String>()
        var dataScores= arrayListOf<String>()

        var long= usuarios.size-1


        for(i in 0..long){
            dataNames.add(usuarios[i].nombre_usuario)
        }

        for(j in 0..long){
            dataScores.add(usuarios[j].score_usuario.toString())
        }

        var recyclerViewNames = findViewById<RecyclerView>(R.id.rvNames).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeActivity)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataNames)
        }

        var recyclerViewScores= findViewById<RecyclerView>(R.id.rvScores).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@PuntajeActivity)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(dataScores)
        }


    }
}