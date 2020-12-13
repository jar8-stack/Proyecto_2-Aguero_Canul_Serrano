package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.Preferences.MyPreferences
import com.example.proyecto_1_aguero_castillo_canul_serrano.R
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_settings.*
import kotlin.random.Random


class SettingsActivity : AppCompatActivity() {

    var cantidadPreguntas = ""
    var cantidadPistas = ""

    var temasActivados = 1

    private var db_values:Database = Database()

    private var flavorCheckBoxes = mutableListOf<CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val misPreferencias = MyPreferences(this)

        val misCantidadDePreguntas = arrayOf("5", "6", "7", "8", "9", "10")
        val cantidadDePistas = arrayOf("1","2","3")

        flavorCheckBoxes.addAll(
            arrayOf(
                findViewById(R.id.vcbarte),
                findViewById(R.id.vcbciencai),
                findViewById(R.id.vcbcine),
                findViewById(R.id.vcbhistoria),
                findViewById(R.id.vcbprogramacion),
                findViewById(R.id.vcbcultura),
            )
        )

        Stetho.initializeWithDefaults(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            db_values.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){}).build()

        vspnopreguntas.setAdapter( ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, misCantidadDePreguntas))
        vspnopistas.setAdapter( ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cantidadDePistas))

        // TRAER EL ID CONFIGURACION DE LA BASE DE DATOS
        var idConfiguracion = misPreferencias.getIdConfiguracion();

        // TRAER LA CANTIDAD DE PREGUNTAS DE LA BASE DE DATOS
        var cantidadPreguntasDB = db.configurationDao().traerNumeroDePreguntas(idConfiguracion);

        // TRAER LA CANTIDAD DE PISTAS DE LA BASE DE DATOS
        var cantidadPistasDB = db.configurationDao().traerNumeroPistas(idConfiguracion);

        // TRAER EL NIVEL DE DIFICULTAD DE LA BASE DE DATOS
        var nivelDificultad = db.configurationDao().traerNivelDificultad(idConfiguracion);

        // TRAER SI LAS PISTAS ESTAN ACTIVADAS PARA LA CONFIGURACION DE ESTE USUARIO
        var pistasActivas = db.configurationDao().getPistasActivas(idConfiguracion);

        //TRAER LOS STATUS DE CADA TEMA
        var statusArte = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Arte"));
        var statusCiencia = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Ciencia"));
        var statusCine = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Cine"));
        var statusHistoria = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Historia"));
        var statusProgramacion = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Programacion"));
        var statusCultura = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Cultura General"));

        cbtodos.isChecked = statusArte && statusCiencia && statusCine && statusHistoria && statusProgramacion && statusCultura

        vcbarte.isChecked = statusArte
        vcbciencai.isChecked = statusCiencia
        vcbcine.isChecked =statusCine
        vcbhistoria.isChecked =statusHistoria
        vcbprogramacion.isChecked =statusProgramacion
        vcbcultura.isChecked =statusCultura

                // SETEAR ESA CANTIDAD DE PREGUNTAS AL SELECT DE PREGUNTAS
        vspnopreguntas.setSelection((vspnopreguntas.getAdapter() as ArrayAdapter<String?>).getPosition("" + cantidadPreguntasDB))

        // SETEAR LA CANTIDAD DE PISTAS AL SELECT DE PISTAS
        vspnopistas.setSelection((vspnopistas.getAdapter() as ArrayAdapter<String?>).getPosition("" + cantidadPistasDB))

        vspnopreguntas.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

                cantidadPreguntas = adapterView.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // vacio
            }
        })

        vspnopistas.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

                cantidadPistas = adapterView.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // vacio
            }
        })

        // SETEAMOS NUESTRA DIFICULTAD AL DE NUESTRA BASE DE DATOS
        if(nivelDificultad == "F"){ //FACIL
            rbtnFacil.isChecked = true
        }else if(nivelDificultad == "M"){ //MEDIO
            rbtnMedio.isChecked = true
        }else if(nivelDificultad == "D"){ //DIFICIL
            rbtnDificil.isChecked = true
        }

        // SETEAMOS NUESTRAS PISTAS SI ESTAN ACTIVAS
        if(pistasActivas){
            vtbpistas.isChecked = true
            txvNoPistas.visibility = View.VISIBLE
            vspnopistas.visibility = View.VISIBLE
        }else{
            vtbpistas.isChecked = false
            txvNoPistas.visibility = View.INVISIBLE
            vspnopistas.visibility = View.INVISIBLE
        }

        vtbpistas.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                txvNoPistas.visibility = View.VISIBLE
                vspnopistas.visibility = View.VISIBLE
            } else {
                // The toggle is disabled
                txvNoPistas.visibility = View.INVISIBLE
                vspnopistas.visibility = View.INVISIBLE
            }
        }

        btnGuardarPreferencias.setOnClickListener { view ->

            var dificultad = ""

            if(rbtnFacil.isChecked){
                dificultad = "F"
            }else if(rbtnMedio.isChecked){
                dificultad = "M"
            }else if(rbtnDificil.isChecked){
                dificultad = "D"
            }

            db.configurationDao().actualizarConfiguracion(cantidadPreguntas.toInt(),dificultad,cantidadPistas.toInt(),vtbpistas.isChecked,idConfiguracion);

            db.ConfigurationThemesDAO().actualizarStatusTema(db.themeDao().getThemeId("Arte"),idConfiguracion,vcbarte.isChecked)
            db.ConfigurationThemesDAO().actualizarStatusTema(db.themeDao().getThemeId("Ciencia"),idConfiguracion,vcbciencai.isChecked)
            db.ConfigurationThemesDAO().actualizarStatusTema(db.themeDao().getThemeId("Cine"),idConfiguracion,vcbcine.isChecked)
            db.ConfigurationThemesDAO().actualizarStatusTema(db.themeDao().getThemeId("Historia"),idConfiguracion,vcbhistoria.isChecked)
            db.ConfigurationThemesDAO().actualizarStatusTema(db.themeDao().getThemeId("Programacion"),idConfiguracion,vcbprogramacion.isChecked)
            db.ConfigurationThemesDAO().actualizarStatusTema(db.themeDao().getThemeId("Cultura General"),idConfiguracion,vcbcultura.isChecked)
        }

        cbtodos.setOnClickListener{ v ->
            if(cbtodos.isChecked){
                flavorCheckBoxes.forEach{ chk ->
                    chk.isChecked = true;
                }
            }else{
                flavorCheckBoxes.forEach{ chk ->
                    chk.isChecked = false;
                }
                flavorCheckBoxes[rand(0,5)].isChecked = true;
            }
        }
    }

    fun validarTemas(view: View) {
        val flavorToShow = mutableListOf<String>()
        flavorCheckBoxes.forEach{ chk ->
            if(chk.isChecked){
                flavorToShow.add(chk.text.toString())
            }
        }

        if(flavorToShow.count() > 5){
            cbtodos.isChecked = true
        }

        if(flavorToShow.count() < 6){
            cbtodos.isChecked = false
        }

        if(flavorToShow.count() < 1){
            flavorCheckBoxes[rand(0,5)].isChecked = true;
        }

        Log.d("CHEK",flavorToShow.joinToString())
    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }
}