package com.example.proyecto_1_aguero_castillo_canul_serrano

data class ThemeQuestion(val strResId: Int, var answers:List<Int> , var correctanswer:Int, var category: String , var answered:Boolean)

data class ThemeQuestionDb(var questionString: String, var answers: List<String>, var correctanswer: String, var category: String, var answered: Boolean,val id:Int)