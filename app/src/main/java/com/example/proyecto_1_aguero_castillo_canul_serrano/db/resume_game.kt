package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resume_game")
data class resume_game(
    @PrimaryKey @ColumnInfo(name = "id")val id:Int,
    @ColumnInfo(name = "resume")var resume:Boolean
)
