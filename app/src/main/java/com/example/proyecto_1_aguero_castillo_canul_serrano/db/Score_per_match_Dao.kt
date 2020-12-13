package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Score_per_match_Dao {
    @Query("SELECT * FROM score_per_match WHERE id_usuario=:idUser")
    fun getScorePerUser(idUser: Int): List<score_per_match>



    @Query("SELECT * FROM score_per_match ORDER  BY score_per_match DESC LIMIT 0, 5")
    fun getFiveScores(): List<score_per_match>

    @Insert
    fun insertScorePerMatch(SPM: score_per_match)
}