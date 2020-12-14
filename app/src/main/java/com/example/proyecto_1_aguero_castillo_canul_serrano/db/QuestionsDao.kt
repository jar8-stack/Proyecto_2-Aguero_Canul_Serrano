package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questions WHERE theme_id= :theme_id")
    fun getQuestionsById(theme_id: Int): List<Questions>

    @Query("UPDATE questions SET active=:active WHERE id=:id")
    fun setActiveQuestion(id:Int,active:Boolean)

    @Query("UPDATE questions SET answered=:answered WHERE id=:id")
    fun setAnsweredQuestion(id: Int,answered: Boolean)

    @Query("UPDATE questions SET answered=0,active=0 WHERE active=1")
    fun resetQuestions()

    @Query("SELECT * FROM questions WHERE active=1")
    fun getActiveQuestions():List<Questions>
}