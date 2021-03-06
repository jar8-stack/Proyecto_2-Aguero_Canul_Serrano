package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
public data class Questions(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "theme_id") val theme_id: Int,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "active")var active:Boolean,
    @ColumnInfo(name = "answered")var answered:Boolean
)