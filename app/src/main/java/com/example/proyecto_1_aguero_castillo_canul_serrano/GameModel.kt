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

    fun madeThis(){
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

            oloDb= ThemeQuestionDb(questionsArt[i].text, listaRespuestas, correctAnswer.text, dbThemeArt.description, false)

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

            oloDbH= ThemeQuestionDb(questionsHistory[i].text, listaRespuestasH, correctAnswerH.text, dbThemeHistory.description, false)

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

            oloDbC= ThemeQuestionDb(questionsCiencia[i].text, listaRespuestasC, correctAnswerC.text, dbThemeCiencia.description, false)

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

            oloDbP= ThemeQuestionDb(questionsProgramacion[i].text, listaRespuestasP, correctAnswerP.text, dbThemeProgramacion.description, false)

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

            oloDbCine= ThemeQuestionDb(questionsCine[i].text, listaRespuestasCine, correctAnswerCine.text, dbThemeCine.description, false)

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

            oloDbCultura= ThemeQuestionDb(questionsCultura[i].text, listaRespuestasCultura, correctAnswerCultura.text, dbThemeCultura.description, false)

            culture_questions.add(oloDbCultura)
        }




    }



    private var temaTodos = misPreferencias.getTemasTodos();

    private var temaArte = misPreferencias.getTemaArte();
    private var temaCiencia = misPreferencias.getTemaCiencia();
    private var temaCine = misPreferencias.getTemaCine();
    private var temaHistoria = misPreferencias.getTemaHistoria();
    private var temaProgramacion = misPreferencias.getTemaProgramacion();
    private var themeCultura = misPreferencias.getTemaCultura();

    private var numeroPreguntas = misPreferencias.getCantidadPreguntas();
    private var dificultad = misPreferencias.getNivelEstablecido() //0->baja  1->media  2->alta
    private var pistasActivas = misPreferencias.getPistasActivas()
    private var numeroPistas = misPreferencias.getCantidadPistas()

    private var currentQuestion = 0
    private var answeredQuestions = 0
    private var correctQuestions: Float = 0f
    private var points : Float = 0f
    private var usarPista =false

    private val questions = listOf<Question>(
        Question(R.string.question_text_1, false, false),
        Question(R.string.question_text_2, false, false),
        Question(R.string.question_text_3, false, false),
        Question(R.string.question_text_4, false, false),
        Question(R.string.question_text_5, false, false),
        Question(R.string.question_text_6, true, false)
    )

    //Theme Questions
    private var arte_questions = arrayListOf<ThemeQuestionDb>(


    )

    //Hola mundo
    private val history_questions = arrayListOf<ThemeQuestionDb>(

    )
    private val ciencia_questions = arrayListOf<ThemeQuestionDb>(

    )
    private val programacion_questions = arrayListOf<ThemeQuestionDb>(

    )
    //te
    private val cine_questions = arrayListOf<ThemeQuestionDb>(

    )
    private val culture_questions = arrayListOf<ThemeQuestionDb>(

    )

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



    fun resPista(){
        numeroPistas--
    }

}

data class prueba(var cate: String, var num: Int)