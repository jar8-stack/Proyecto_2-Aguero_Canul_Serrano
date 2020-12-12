package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionAnswersDao {
    @Query("SELECT * FROM question_answers WHERE id= :id_question")
    fun getQuestionAnswersById(id_question: Int): List<question_answers>


}