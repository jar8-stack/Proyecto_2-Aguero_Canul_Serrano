package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questions WHERE theme_id= :theme_id")
    fun getQuestionsById(theme_id: Int): List<Questions>
}