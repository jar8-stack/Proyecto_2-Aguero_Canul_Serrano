package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.Preferences.MyPreferences
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.AppDatabase
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.Questions
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.Theme
import com.example.proyecto_1_aguero_castillo_canul_serrano.db.question_answers
import kotlin.properties.Delegates

class GameModel(application: Application) : AndroidViewModel(application) {
    private var db_values:Database = Database()
    val misPreferencias = MyPreferences(getApplication())

    private var artList= listOf<Theme>()

    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        db_values.getName()
    ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){

    }).build()

    //OBTENEMOS EL ID CONFIGURACIONES DEL USUARIO ACTUAL
    var idConfiguracion = misPreferencias.getIdConfiguracion();

    private var temaTodos = misPreferencias.getTemasTodos();


    //OBTENEMOS LOS TEMAS ACTIVOS DE LA BASE DE DATOS
    private var temaArte = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Arte"));
    private var temaCiencia = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Ciencia"));
    private var temaCine = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Cine"));
    private var temaHistoria = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Historia"));
    private var temaProgramacion = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Programacion"));
    private var themeCultura = db.ConfigurationThemesDAO().traerStatusTema(idConfiguracion,db.themeDao().getThemeId("Cultura General"));

    //OBTENEMOS LOS NUMERO DE PREGUNTAS DE LA BASE DE DATOS
    private var numeroPreguntas = db.configurationDao().traerNumeroDePreguntas(idConfiguracion);


    private var dificultad = misPreferencias.getNivelEstablecido() //0->baja  1->media  2->alta

    //OBTENEMOS SI LAS PISTAS ESTAN ACTIVAS DE LA BASE DE DATOS
    private var pistasActivas = db.configurationDao().getPistasActivas(idConfiguracion);

    //OBTENEMOS EL NUMERO DE PISTAS DE LA BASE DE DATOS
    private var numeroPistas = db.configurationDao().traerNumeroPistas(idConfiguracion);

    private var currentQuestion = 0
    private var answeredQuestions = 0
    private var correctQuestions: Float = 0f
    private var points : Float = 0f
    private var usarPista =false

    private var resume_match:Boolean = false

    fun madethisActiveQuestions(){
        var activeQuestions = db.questionDao().getActiveQuestions()

        for (question in activeQuestions){
            var listaRespuestas= arrayListOf<String>()
            lateinit var correct_answer:String

            var dbTheme = db.themeDao().getThemeById(question.theme_id)
            var answersQuestion = db.questionAnswersDao().getQuestionAnswersById(question.id)

            for (answer in answersQuestion){
                listaRespuestas.add(answer.text)
                if (answer.correct_answer){
                    correct_answer = answer.text
                }
            }
            var f_question = ThemeQuestionDb(question.text,listaRespuestas,correct_answer,dbTheme.description,question.answered,question.id)
            final_questions_toshow.add(f_question)
        }

    }
    fun setActiveMatchValues(){
        var match_values = db.UserMatchesDao().getMatch()
        answeredQuestions = match_values.answered_question
        correctQuestions = match_values.correct_questions
        points = match_values.points
        usarPista = match_values.use_hint
        db.UserMatchesDao().deleteMatch()
    }
    fun madeThis(){
        db.resumeGameDao().updateResume(true)
        // *************** ARTE ***********************
        var dbThemeArt= db.themeDao().getThemeByName("Arte")
        var questionsArt= db.questionDao().getQuestionsById(dbThemeArt.id)
        var answerQuestionsPerQuestion= listOf<question_answers>()
        lateinit var correctAnswer: question_answers

        lateinit var oloDb: ThemeQuestionDb
        var listaRespuestas= arrayListOf<String>()


        for(i in 0..questionsArt.size-1){

            answerQuestionsPerQuestion= db.questionAnswersDao().getQuestionAnswersById(questionsArt[i].id)
            for(j in 0..answerQuestionsPerQuestion.size-1){
                if(answerQuestionsPerQuestion[j].correct_answer){
                    correctAnswer= answerQuestionsPerQuestion[j]
                    break
                }
            }

            for(j in 0..answerQuestionsPerQuestion.size-1){
                listaRespuestas.add(answerQuestionsPerQuestion[j].text)
            }

            oloDb= ThemeQuestionDb(questionsArt[i].text, listaRespuestas, correctAnswer.text, dbThemeArt.description, false,questionsArt[i].id)

            arte_questions.add(oloDb)
        }

        //*********************HISTORIA************************
        var dbThemeHistory= db.themeDao().getThemeByName("Historia")
        var questionsHistory= db.questionDao().getQuestionsById(dbThemeHistory.id)
        var answerQuestionsPerQuestionH= listOf<question_answers>()
        lateinit var correctAnswerH: question_answers

        lateinit var oloDbH: ThemeQuestionDb
        var listaRespuestasH= arrayListOf<String>()



        for(i in 0..questionsHistory.size-1){

            answerQuestionsPerQuestionH= db.questionAnswersDao().getQuestionAnswersById(questionsHistory[i].id)
            for(j in 0..answerQuestionsPerQuestionH.size-1){
                if(answerQuestionsPerQuestionH[j].correct_answer){
                    correctAnswerH= answerQuestionsPerQuestionH[j]
                    break
                }
            }

            for(j in 0..answerQuestionsPerQuestionH.size-1){
                listaRespuestasH.add(answerQuestionsPerQuestionH[j].text)
            }

            oloDbH= ThemeQuestionDb(questionsHistory[i].text, listaRespuestasH, correctAnswerH.text, dbThemeHistory.description, false,questionsHistory[i].id)

            history_questions.add(oloDbH)
        }

        //*************CIENCIA************
        var dbThemeCiencia= db.themeDao().getThemeByName("Ciencia")
        var questionsCiencia= db.questionDao().getQuestionsById(dbThemeCiencia.id)
        var answerQuestionsPerQuestionC= listOf<question_answers>()
        lateinit var correctAnswerC: question_answers

        lateinit var oloDbC: ThemeQuestionDb
        var listaRespuestasC= arrayListOf<String>()



        for(i in 0..questionsCiencia.size-1){

            answerQuestionsPerQuestionC= db.questionAnswersDao().getQuestionAnswersById(questionsCiencia[i].id)
            for(j in 0..answerQuestionsPerQuestionC.size-1){
                if(answerQuestionsPerQuestionC[j].correct_answer){
                    correctAnswerC= answerQuestionsPerQuestionC[j]
                    break
                }
            }

            for(j in 0..answerQuestionsPerQuestionC.size-1){
                listaRespuestasC.add(answerQuestionsPerQuestionC[j].text)
            }

            oloDbC= ThemeQuestionDb(questionsCiencia[i].text, listaRespuestasC, correctAnswerC.text, dbThemeCiencia.description, false,questionsCiencia[i].id)

            ciencia_questions.add(oloDbC)
        }

        //***********PROGRAMACION*************
        var dbThemeProgramacion= db.themeDao().getThemeByName("Programacion")
        var questionsProgramacion= db.questionDao().getQuestionsById(dbThemeProgramacion.id)
        var answerQuestionsPerQuestionP= listOf<question_answers>()
        lateinit var correctAnswerP: question_answers

        lateinit var oloDbP: ThemeQuestionDb
        var listaRespuestasP= arrayListOf<String>()



        for(i in 0..questionsProgramacion.size-1){

            answerQuestionsPerQuestionP= db.questionAnswersDao().getQuestionAnswersById(questionsProgramacion[i].id)
            for(j in 0..answerQuestionsPerQuestionP.size-1){
                if(answerQuestionsPerQuestionP[j].correct_answer){
                    correctAnswerP= answerQuestionsPerQuestionP[j]
                    break
                }
            }

            for(j in 0..answerQuestionsPerQuestionP.size-1){
                listaRespuestasP.add(answerQuestionsPerQuestionP[j].text)
            }

            oloDbP= ThemeQuestionDb(questionsProgramacion[i].text, listaRespuestasP, correctAnswerP.text, dbThemeProgramacion.description, false,questionsProgramacion[i].id)

            programacion_questions.add(oloDbP)
        }

        //********CINE*************
        var dbThemeCine= db.themeDao().getThemeByName("Cine")
        var questionsCine= db.questionDao().getQuestionsById(dbThemeCine.id)
        var answerQuestionsPerQuestionCine= listOf<question_answers>()
        lateinit var correctAnswerCine: question_answers

        lateinit var oloDbCine: ThemeQuestionDb
        var listaRespuestasCine= arrayListOf<String>()



        for(i in 0..questionsCine.size-1){

            answerQuestionsPerQuestionCine= db.questionAnswersDao().getQuestionAnswersById(questionsCine[i].id)
            for(j in 0..answerQuestionsPerQuestionCine.size-1){
                if(answerQuestionsPerQuestionCine[j].correct_answer){
                    correctAnswerCine= answerQuestionsPerQuestionCine[j]
                    break
                }
            }

            for(j in 0..answerQuestionsPerQuestionCine.size-1){
                listaRespuestasCine.add(answerQuestionsPerQuestionCine[j].text)
            }

            oloDbCine= ThemeQuestionDb(questionsCine[i].text, listaRespuestasCine, correctAnswerCine.text, dbThemeCine.description, false,questionsCine[i].id)

            cine_questions.add(oloDbCine)
        }

        //************CULTURA***********
        var dbThemeCultura= db.themeDao().getThemeByName("Cultura General")
        var questionsCultura= db.questionDao().getQuestionsById(dbThemeCultura.id)
        var answerQuestionsPerQuestionCultura= listOf<question_answers>()
        lateinit var correctAnswerCultura: question_answers

        lateinit var oloDbCultura: ThemeQuestionDb
        var listaRespuestasCultura= arrayListOf<String>()


        for(i in 0..questionsCultura.size-1){

            answerQuestionsPerQuestionCultura= db.questionAnswersDao().getQuestionAnswersById(questionsCultura[i].id)
            for(j in 0..answerQuestionsPerQuestionCultura.size-1){
                if(answerQuestionsPerQuestionCultura[j].correct_answer){
                    correctAnswerCultura= answerQuestionsPerQuestionCultura[j]
                    break
                }
            }

            for(j in 0..answerQuestionsPerQuestionCultura.size-1){
                listaRespuestasCultura.add(answerQuestionsPerQuestionCultura[j].text)
            }

            oloDbCultura= ThemeQuestionDb(questionsCultura[i].text, listaRespuestasCultura, correctAnswerCultura.text, dbThemeCultura.description, false,questionsCultura[i].id)

            culture_questions.add(oloDbCultura)
        }
    }
    private val questions = listOf<Question>(
        Question(R.string.question_text_1, false, false),
        Question(R.string.question_text_2, false, false),
        Question(R.string.question_text_3, false, false),
        Question(R.string.question_text_4, false, false),
        Question(R.string.question_text_5, false, false),
        Question(R.string.question_text_6, true, false)
    )
    //Theme Questions
    private var arte_questions = arrayListOf<ThemeQuestionDb>()
    private val history_questions = arrayListOf<ThemeQuestionDb>()
    private val ciencia_questions = arrayListOf<ThemeQuestionDb>()
    private val programacion_questions = arrayListOf<ThemeQuestionDb>()
    private val cine_questions = arrayListOf<ThemeQuestionDb>()
    private val culture_questions = arrayListOf<ThemeQuestionDb>()

    private val final_array2 = arrayListOf<ThemeQuestionDb>()
    private var final_questions_toshow = arrayListOf<ThemeQuestionDb>()

    fun showQuestions(){
        var finalList= arrayListOf<ThemeQuestionDb>()
        val listaOlo = arrayListOf<prueba>(
            prueba("Arte", 0),
            prueba("Ciencia", 0),
            prueba("Cine", 0),
            prueba("Historia", 0),
            prueba("Programacion", 0),
            prueba("Cultura General", 0)
        )
        var listaOlo2= arrayListOf<prueba>()


        for(element in final_array2){
            var cate= element.category

            when(cate){
                "Historia"->{
                    listaOlo.get(3).num+=1
                }
                "Ciencia"->{
                    listaOlo.get(1).num+=1
                }
                "Arte"->{
                    listaOlo.get(0).num+=1
                }
                "Programacion"->{
                    listaOlo.get(4).num+=1
                }
                "Cine"->{
                    listaOlo.get(2).num+=1
                }
                "Cultura General"->{
                    listaOlo.get(5).num+=1
                }
            }
        }



        for(element in listaOlo){
            if(element.num !=0){
                listaOlo2.add(element)
            }
        }

        var listCate= recu(numeroPreguntas, listaOlo2.size)

        for(i in 0..(listaOlo2.size-1)){
            for(j in 0..final_array2.size){
                if(j<listCate[i] && final_array2.get(j).category == listaOlo2.get(i).cate){
                    finalList.add(final_array2.get(j))
                }else{
                    for(l in 0..(listaOlo2.get(i).num-1)){
                        final_array2.removeAt(0)
                    }
                    break
                }
            }
        }

        final_questions_toshow = finalList
        setActiveOnDb(final_questions_toshow)

    }
    private fun setActiveOnDb(final_questions:ArrayList<ThemeQuestionDb>){
        for (question in final_questions){
            db.questionDao().setActiveQuestion(question.id,true)
        }
    }
    fun setAnswersOnDb(){
        for (question in final_questions_toshow){
            if (question.answered){
                db.questionDao().setAnsweredQuestion(question.id,true)
            }
        }
    }
    //funciones recursivas -
    val listaNums= arrayListOf<Int>()
    fun recu(numQ: Int, numC: Int): MutableList<Int> {

        return if (numQ - (numQ / numC) == 0) {
            //println(numQ)
            listaNums.add(numQ)
            return listaNums
        } else {
            //println(numQ/numC)
            listaNums.add(numQ / numC)
            recu((numQ - (numQ / numC)), (numC - 1))
        }
    }
    val listaNums2= arrayListOf<Int>()
    fun recu2(numQ: Int, numC: Int): MutableList<Int> {

        return if (numQ - (numQ / numC) == 0) {
            //println(numQ)
            listaNums2.add(numQ)
            return listaNums2
        } else {
            //println(numQ/numC)
            listaNums2.add(numQ / numC)
            recu2((numQ - (numQ / numC)), (numC - 1))
        }
    }

    //funcion para filtrar preguntas
    public fun filtrateQuestions()
    {
        var numOfTrueThemes = 0 //la cantidad de temas elegidos
        var indexthemes = arrayListOf<Int>() //los indices de los temas elegidos
        var themesCheck = arrayOf(temaArte,temaCiencia,temaCine,temaHistoria,temaProgramacion,themeCultura)

        for ((i,value) in themesCheck.withIndex())
        {
            if(value){
                //println(numOfTrueThemes)
                indexthemes.add(i)
                numOfTrueThemes++
            }
        }
        var countsArrayDiv = recu2(10,numOfTrueThemes)
        println(countsArrayDiv)
        //println(numOfTrueThemes)
        //println(countsArrayDiv)
        for (i in 0 until numOfTrueThemes)
        {
            //print(i)
            //println(indexthemes[i])
            when (indexthemes[i]){
                0 -> chooseQuestions(arte_questions,countsArrayDiv[i])
                1 -> chooseQuestions(ciencia_questions,countsArrayDiv[i])
                2 -> chooseQuestions(cine_questions,countsArrayDiv[i])
                3 -> chooseQuestions(history_questions,countsArrayDiv[i])
                4 -> chooseQuestions(programacion_questions,countsArrayDiv[i])
                5 -> chooseQuestions(culture_questions,countsArrayDiv[i])
            }
        }
        println(final_array2)
    }

    var randoms = arrayListOf<Int>()
    fun chooseQuestions(questions:List<ThemeQuestionDb>, count:Int)
    {

        for (i in 0 until count)
        {
            var iquesToShow = makeRandom()
            final_array2.add(questions[iquesToShow])
        }
        randoms = arrayListOf<Int>()
    }
    fun makeRandom() :Int
    {
        var num = (0..9).random()
        var i = 0
        outer@while (i < randoms.size){
            while (num == randoms[i]){
                num = (0..9).random()
                i = 0
                continue@outer
            }
            i++
        }

        randoms.add(num)
        return num
    }
    fun pistaPena() :Float {
        //var dificultad= arrayOf<String>("Baja", "Media", "Alta")
        //var numQuestions= 10
        var regla3= 10/numeroPreguntas
        var res= 0f
        var cate= dificultad

        when(cate){
            0->{
                res= (regla3* 0.2).toFloat()
            }
            1->{
                res= (regla3* 0.3).toFloat()
            }
            2 ->{
                res= (regla3* 0.4).toFloat()
            }
        }
        return res
    }

    fun getQuestionsToShow():List<ThemeQuestionDb>{ return final_questions_toshow}

    //view
    fun numOfQuestions(): Int { return numeroPreguntas }
    fun currentQuestionNum(): Int { return currentQuestion }
    fun currentQuestionObj(): ThemeQuestionDb { return final_questions_toshow[currentQuestion] }

    fun correctQuestions(): Float { return correctQuestions }
    fun increaseCorrectQuestions()
    {
        correctQuestions++
        if(usarPista && pistasActivas){
            var toincrease = pistaPena()
            toincrease = 1-toincrease
            points +=toincrease
        } else{
            points++
        }
        println(points)
    }

    fun getPoints():Float{return points}
    fun answeredQuestions(): Int { return answeredQuestions }
    fun increaseAnsweredQuestions() { answeredQuestions++ }

    fun nextQuestion() { currentQuestion = (currentQuestion + 1) % final_questions_toshow.size }
    fun previousQuestion() { currentQuestion = (currentQuestion - 1) % final_questions_toshow.size }

    fun updateCurrentQuestion(value: Int) { currentQuestion = value }

    fun getDificult(): Int { return dificultad}

    fun pistasActivas():Boolean{return pistasActivas}
    fun getNumPistas(): Int{ return numeroPistas}

    fun getUsarPista(): Boolean {return usarPista}

    fun setUsarPista(value:Boolean){ usarPista= value}
    fun setResume(value:Boolean){resume_match = value}
    fun getResume():Boolean{return resume_match}

    fun resPista(){
        numeroPistas--
    }

}

data class prueba(var cate: String, var num: Int)