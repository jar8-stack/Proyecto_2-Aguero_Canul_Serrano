package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ResumeGameDao {
    @Query("SELECT * FROM resume_game")
    fun getResume():resume_game

    @Query("UPDATE resume_game SET resume=:value WHERE id=1 ")
    fun updateResume(value: Boolean)
}