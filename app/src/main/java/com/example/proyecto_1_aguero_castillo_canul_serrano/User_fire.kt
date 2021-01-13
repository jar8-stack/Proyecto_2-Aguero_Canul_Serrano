package com.example.proyecto_1_aguero_castillo_canul_serrano

data class User_fire(var userName: String, val buscando_partida: String, val puntaje_por_partida: Int) {
    constructor() : this("", "", 0){

    }
}