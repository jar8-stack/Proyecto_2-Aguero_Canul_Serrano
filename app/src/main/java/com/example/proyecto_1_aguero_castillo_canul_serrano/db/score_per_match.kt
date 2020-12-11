package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_per_match")
data class score_per_match (
    @PrimaryKey @ColumnInfo(name = "id_match") val id_match: Int,
    @ColumnInfo(name = "id_usuario") var id_usuario: Int,
    @ColumnInfo(name = "score_per_match") var score_per_match: Int,
    @ColumnInfo(name = "date_match") var date_match: String
)