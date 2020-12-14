package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface Score_per_match_Dao {
    @Query("SELECT * FROM score_per_match WHERE id_usuario=:idUser")
    fun getScorePerUser(idUser: Int): List<score_per_match>

    @Query("SELECT * FROM score_per_match WHERE id_usuario=:idUser order by score_per_match DESC")
    fun getScorePerUserDesc(idUser: Int): List<score_per_match>

    @Query("SELECT * FROM score_per_match WHERE id_usuario=:idUser order by date_match DESC")
    fun getScorePerUserDate(idUser: Int): List<score_per_match>


    @Query("SELECT * FROM score_per_match GROUP BY id_usuario ORDER  BY score_per_match DESC LIMIT 0, 5")
    fun getFiveScores(): List<score_per_match>

    @Query("INSERT INTO score_per_match(id_usuario, score_per_match, date_match, uso_pista) VALUES(:idUsuario, :score_perMatch, :date, :uso_pista)")
    fun insertUser(idUsuario:Int,score_perMatch:Int,date: String, uso_pista: Boolean)
}