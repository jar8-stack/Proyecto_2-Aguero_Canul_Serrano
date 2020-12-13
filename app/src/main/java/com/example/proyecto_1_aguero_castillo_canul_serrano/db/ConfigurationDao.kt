package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.*

@Dao
interface ConfigurationDao {

    @Query("SELECT * FROM configurations")
    fun getThemes(): List<Configuration>

    @Update
    fun updateConfiguration(configuration: Configuration)

    @Update
    fun updateConfiguration(configuration: List<Configuration>)

    @Insert
    fun insertConfiguration(configuration: Configuration)

    @Query("INSERT INTO configurations(number_questions, dificulty, number_tracks,tracks_enabled) VALUES(:numberQuestion, :dificultyLevel, :numberTracks, :tracksEnabled)")
    fun insertConfiguration(numberQuestion:Int,dificultyLevel:String,numberTracks:Int,tracksEnabled:Boolean)

    @Query("SELECT id_configuration from configurations order by id_configuration DESC limit 1")
    fun traerUltimoIdConfiguraciones() : Int

    @Delete
    fun deleteConfiguration(configuration: Configuration)

    @Query("SELECT number_questions FROM configurations WHERE id_configuration = :idConfigurationt")
    fun traerNumeroDePreguntas(idConfigurationt: Int) : Int

    @Query("SELECT number_tracks FROM configurations WHERE id_configuration = :idConfigurationg")
    fun traerNumeroPistas(idConfigurationg: Int) : Int

    @Query("SELECT dificulty FROM configurations WHERE id_configuration = :idConfigurationx")
    fun traerNivelDificultad(idConfigurationx: Int) : String

    @Query("SELECT tracks_enabled FROM configurations WHERE id_configuration = :idConfigurationc")
    fun getPistasActivas(idConfigurationc: Int) : Boolean

    @Query("UPDATE configurations SET number_questions = :NnumberQuestion, dificulty = :NdificultyLevel, number_tracks = :NnumberTracks, tracks_enabled = :NtracksEnabled WHERE id_configuration = :idConfiguracion")
    fun actualizarConfiguracion(NnumberQuestion:Int,NdificultyLevel:String,NnumberTracks:Int,NtracksEnabled:Boolean, idConfiguracion: Int)
}