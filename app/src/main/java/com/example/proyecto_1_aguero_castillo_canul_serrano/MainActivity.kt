package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.Preferences.MyPreferences
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class MainActivity : AppCompatActivity() {
    private val dbValues:Database = Database()
    private val model: GameModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val misPreferencias = MyPreferences(this)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            dbValues.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){
        }).build()

        //Hola mundo
        nombreUsuario.text =
            misPreferencias.getNombreUsuario() + " - " + misPreferencias.getIdUsuario();

        btnScore.setOnClickListener {
            val intent: Intent = Intent(this, PuntajeActivity::class.java)
            startActivity(intent)
        }

        btnCerrarSesion.setOnClickListener {
            misPreferencias.setLogeado(false)
            db.UserMatchesDao().deleteMatch()
            db.resumeGameDao().updateResume(false)
            val intent: Intent = Intent(this, SelectPlayerActivity::class.java)
            startActivity(intent)
        }

        btnSettings.setOnClickListener {
            val intent: Intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        btnGame.setOnClickListener {


            val mAlertDialogJuego = AlertDialog.Builder(this@MainActivity)
            mAlertDialogJuego.setTitle("Returnar juego")
            mAlertDialogJuego.setMessage("¿Desea reanudar partida?")
            mAlertDialogJuego.setPositiveButton("Si") MainActivity@{ dialog, id ->
                //Reanudar partida guardada
                val intent: Intent = Intent(this, GameActivity::class.java).apply {
                    putExtra("userName", nombreUsuario.text)
                }
                startActivity(intent)
            }

            mAlertDialogJuego.setNegativeButton("No") { dialog, id ->
                //Partida nueva
                db.UserMatchesDao().deleteMatch()
                db.questionDao().resetQuestions()
                db.resumeGameDao().updateResume(false)
                val intent: Intent = Intent(this, GameActivity::class.java).apply {
                    putExtra("userName", nombreUsuario.text)
                }
                startActivity(intent)
            }
            mAlertDialogJuego.show()


        }
    }
}
