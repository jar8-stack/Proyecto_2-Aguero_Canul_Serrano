package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.facebook.stetho.Stetho
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    private lateinit var questionCount:TextView
    private lateinit var questionTextView: TextView
    private lateinit var questionsAnswered:TextView
    private lateinit var yourResultsText:TextView
    private lateinit var correctAnswersText:TextView
    private lateinit var resultText: TextView
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton : Button
    private lateinit var previousButton:Button
    private lateinit var btnPista: Button

    private lateinit var listaRespuestas: ListView


    private val model : GameModel by viewModels()
    private val dbValues:Database = Database()


    override fun onBackPressed() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            dbValues.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback() {
        }).build()

        val userName= intent.getStringExtra("userName")

        val mAlertDialog= AlertDialog.Builder(this@GameActivity)
        mAlertDialog.setTitle("Salir de partida")
        mAlertDialog.setMessage("¿Desea salir de la partida?")
        mAlertDialog.setPositiveButton("Si") MainActivity@{ dialog, id ->
            //Toast.makeText(this@GameActivity, "Si", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
            //pendiente id usuario
            var id_user = 0
            var currentQuestion = model.currentQuestionNum()
            var answeredQuestions = model.answeredQuestions()
            var correctQuestions = model.correctQuestions()
            var points = model.getPoints()
            var useHint = model.getUsarPista()

            model.setAnswersOnDb()
            db.UserMatchesDao().insertMatch(
                id_user.toString().toInt(),
                currentQuestion,
                answeredQuestions,
                correctQuestions,
                points,
                useHint
            )
            return@MainActivity
        }

        mAlertDialog.setNegativeButton("No"){ dialog, id ->
            dialog.dismiss()
        }

        mAlertDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        Stetho.initializeWithDefaults(this)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            dbValues.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback() {

        }).build()



        //**********************Llenar las preguntas******************

        questionCount = findViewById(R.id.questionCount)
        questionsAnswered = findViewById(R.id.questionsAnswered)
        questionTextView = findViewById(R.id.question_text)
        yourResultsText = findViewById(R.id.yourResultsText)
        resultText = findViewById(R.id.resultText)
        correctAnswersText = findViewById(R.id.correctAnswersText)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        btnPista= findViewById(R.id.botonPista)


        listaRespuestas= findViewById(R.id.respuestas)

        var resume = db.resumeGameDao().getResume()
        print(resume.resume)
        if (!resume.resume){
            print("Partida desde 0")
            model.madeThis()
            model.filtrateQuestions()
            model.showQuestions()
        }else{
            print("Encontre partida")
            model.madethisActiveQuestions()
            model.setActiveMatchValues()
        }


        if(model.pistasActivas())
        {
            btnPista.visibility = View.VISIBLE
            btnPista.text= "Pistas:"+model.getNumPistas().toString()
        }

        questionTextView.setText(model.currentQuestionObj().questionString)
        questionCount.setText((model.currentQuestionNum() + 1).toString() + "/" + model.numOfQuestions())
        questionsAnswered.setText(" : " + model.answeredQuestions())

        if(model.answeredQuestions() == model.numOfQuestions()){
            yourResultsText.setText(R.string.final_1)
            resultText.setText(calculateFinalResult())

            correctAnswersText.setText(
                model.correctQuestions().toInt().toString() + " correct answers"
            )
        }


        //resultado True button

        //resultado False button

        //next button
        nextButton.setOnClickListener{ view: View ->
            model.nextQuestion()
            questionTextView.setText(model.currentQuestionObj().questionString)
            questionCount.setText((model.currentQuestionNum() + 1).toString() + "/" + model.numOfQuestions())

            asignedAnswers()


        }
        //previous button
        previousButton.setOnClickListener{ view: View ->
            model.previousQuestion()
            if(model.currentQuestionNum() == -1) model.updateCurrentQuestion(model.numOfQuestions() - 1)
            questionTextView.setText(model.currentQuestionObj().questionString)
            questionCount.setText((model.currentQuestionNum() + 1).toString() + "/" + model.numOfQuestions())

            asignedAnswers()
        }


        btnPista.setOnClickListener{ view: View ->


            if(model.getNumPistas() >0 && !model.currentQuestionObj().answered){

                var correctAnswer= model.getQuestionsToShow().get(model.currentQuestionNum()).correctanswer

                var correctAnswerString: String = correctAnswer

                var listaFinal= arrayListOf<String>()

                model.resPista()
                var text="Pistas:"+(model.getNumPistas()).toString()
                btnPista.text= text

                var tamañoLista= listaRespuestas.size


                for(i in 0..tamañoLista-1){
                    var itemValue= listaRespuestas.getItemAtPosition(i) as String
                    listaFinal.add(itemValue)

                }



                var cont=0
                for(element in listaFinal){
                    if(listaFinal[cont]!= correctAnswerString){
                        listaFinal.removeAt(cont)
                        cont++
                        break
                    }else{
                        cont++
                    }
                }

                listaRespuestas.adapter=
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaFinal)


                model.setUsarPista(true)
            }

        }


        listaRespuestas.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                // value of item that is clicked
                val itemValue = listaRespuestas.getItemAtPosition(position) as String

                var correctAnswer= model.getQuestionsToShow().get(model.currentQuestionNum()).correctanswer

                var correctAnswerString: String = correctAnswer

                if(!model.currentQuestionObj().answered)
                {
                    if(correctAnswerString == itemValue){

                        // Toast the values
                        Toast.makeText(
                            applicationContext,
                            "Dios mio le atinaste",
                            Toast.LENGTH_LONG
                        ).show()
                        model.increaseCorrectQuestions()
                        model.setUsarPista(false)

                    }else{
                        // Toast the values
                        Toast.makeText(applicationContext, "Nada chavo", Toast.LENGTH_LONG).show()
                    }
                    model.currentQuestionObj().answered = true;
                    updateAnsweredQuestions()
                }else{
                    showToast("Esta pregunta ya fue respondida")
                }
            }
        }
        //val string: String = getString(R.string.your_string_id)
        asignedAnswers()
    }


    fun updateAnsweredQuestions() {
        model.increaseAnsweredQuestions()
        questionsAnswered.setText(" : " + model.answeredQuestions())

        val userName= intent.getStringExtra("userName")

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            dbValues.getName()
        ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback() {
        }).build()


        if(model.answeredQuestions() ==  model.numOfQuestions()){
            yourResultsText.setText(R.string.final_1)
            resultText.setText(calculateFinalResult())

            correctAnswersText.setText(
                model.correctQuestions().toInt().toString() + " correct answers"
            )



            var userNameComplete=""

            var numeric= true
            try{
                val num= parseInt(userName[0].toString())
            }catch (e: NumberFormatException){
                numeric= false
            }

            if(numeric){
                for(i in 0..userName.length-1){
                    if(i > 3){
                        userNameComplete= userNameComplete+ userName[i]
                    }
                }
            }else{
                for(i in 0..userName.length-1){
                    if(userName[i].toString() != " "){
                        userNameComplete= userNameComplete+ userName[i]
                    }else{
                        break
                    }
                }
            }






            var usuarioXd= db.userDao().getUserByName(userNameComplete)





            var fechaActual= obtenerFechaActual("America/Mexico_City")

            var resultadoFinal= ""
            var resultadoString= calculateFinalResult().toString()
            for(i in 0..resultadoString.length-2){
                resultadoFinal= resultadoFinal +resultadoString[i]
            }

            var scoreGlobal= usuarioXd.score_usuario+resultadoFinal.toInt()

            db.userDao().updateUserById(usuarioXd.id_usuario, scoreGlobal)

            var usoPista= model.getBoolPista()

            if(usoPista){
                if (fechaActual != null) {
                    db.ScorePerMatch().insertUserScore(usuarioXd.id_usuario, resultadoFinal.toInt(), fechaActual, true)
                }
            }else{
                if (fechaActual != null) {
                    db.ScorePerMatch().insertUserScore(usuarioXd.id_usuario, resultadoFinal.toInt(), fechaActual, false)
                }
            }






            val intent = Intent(this, PuntajeActivityFinalIndividual::class.java)
            startActivity(intent)
        }
    }


    fun obtenerFechaActual(zonaHoraria: String?): String? {
        val formato = "dd-MM-yyyy"
        return obtenerFechaConFormato(formato, zonaHoraria)
    }

    @SuppressLint("SimpleDateFormat")
    fun obtenerFechaConFormato(formato: String?, zonaHoraria: String?): String? {
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.getTime()
        val sdf: SimpleDateFormat
        sdf = SimpleDateFormat(formato)
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria))
        return sdf.format(date)
    }

    fun calculateFinalResult(): String {
        var result:Int = ((model.getPoints()/model.numOfQuestions())*100).toInt()
        println(
            "(" + model.getPoints().toString() + "/" + model.numOfQuestions().toString() + ") *100"
        )
        return "$result%"
    }

    fun calculateResultWithoutPercent(): Int {
        var result:Int = ((model.getPoints()/model.numOfQuestions())*100).toInt()
        return result
    }

    fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun asignedAnswers(){
        var reqId= model.getQuestionsToShow().get(model.currentQuestionNum()).answers


        var dificultad= model.getDificult()

        var listRes= arrayListOf<String>()

        var correctAnswer= model.getQuestionsToShow().get(model.currentQuestionNum()).correctanswer

        var correctAnswerString: String = correctAnswer

        listRes.add(correctAnswerString)


        var lisResPrueba= arrayListOf<String>()
        var cont2=0
        var contSave=0
        when(dificultad){
            0 -> {
                while (listRes.size < 2) {
                    for (element in reqId) {
                        cont2++
                        var string: String = element

                        if (string == correctAnswerString) {
                            contSave = cont2 - 1
                        }
                        lisResPrueba.add(string)
                    }

                    lisResPrueba.removeAt(contSave)

                    shuffle(lisResPrueba)

                    listRes.add(lisResPrueba.get(0))


                }
            }
            1 -> {
                while (listRes.size < 3) {
                    for (element in reqId) {
                        cont2++
                        var string: String = element

                        if (string == correctAnswerString) {
                            contSave = cont2 - 1
                        }
                        lisResPrueba.add(string)
                    }

                    lisResPrueba.removeAt(contSave)

                    shuffle(lisResPrueba)

                    for (i in 0..1) {
                        listRes.add(lisResPrueba.get(i))
                    }
                }
            }
            2 -> {
                while (listRes.size < 4) {
                    for (element in reqId) {
                        cont2++
                        var string: String = element

                        if (string == correctAnswerString) {
                            contSave = cont2 - 1
                        }
                        lisResPrueba.add(string)
                    }

                    lisResPrueba.removeAt(contSave)

                    shuffle(lisResPrueba)

                    for (i in 0..2) {
                        listRes.add(lisResPrueba.get(i))
                    }
                }
            }
        }


        shuffle(listRes)

        listaRespuestas.adapter=
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listRes)
    }

    fun <T> shuffle(list: MutableList<T>)
    {
        // start from end of the list
        for (i in list.size - 1 downTo 1)
        {
            // get a random index j such that 0 <= j <= i
            val j = Random.nextInt(i + 1)

            // swap element at i'th position in the list with element at j'th position
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
    }
}