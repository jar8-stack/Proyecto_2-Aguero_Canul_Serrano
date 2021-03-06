package com.example.proyecto_1_aguero_castillo_canul_serrano.Preferences

import android.content.Context

class MyPreferences(context: Context) {

    val PREFERENCES_NAME = "SharedPreferencesGame"
    val PREFERENCES_NOMBRE_USUARIO = "SharedPreferencesNombreUsuario"
    val PREFERENCES_ID_USUARIO = "SharedPreferencesIdUsuario"
    val PREFERENCES_LOGEADO = "SharedPreferencesLogeado"
    val PREFERENCES_ID_CONFIGURACION = "SharedPreferencesIdConfiguracion"

    val preferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)

    fun getIdConfiguracion() : Int{
        return preferences.getInt(PREFERENCES_ID_CONFIGURACION, 0)
    }

    fun setIdConfiguracion(IdConfiguracion : Int){
        val editor = preferences.edit()
        editor.putInt(PREFERENCES_ID_CONFIGURACION, IdConfiguracion)
        editor.apply()
    }

    fun getLogeado() : Boolean{
        return preferences.getBoolean(PREFERENCES_LOGEADO, false)
    }

    fun setLogeado(Login : Boolean){
        val editor = preferences.edit()
        editor.putBoolean(PREFERENCES_LOGEADO, Login)
        editor.apply()
    }

    fun getNombreUsuario() : String? {
        return preferences.getString(PREFERENCES_NOMBRE_USUARIO,"");
    }

    fun setNombreUsuario(Nombre : String){
        val editor = preferences.edit()
        editor.putString(PREFERENCES_NOMBRE_USUARIO, Nombre)
        editor.apply()
    }

    fun getIdUsuario() : String? {
        return preferences.getString(PREFERENCES_ID_USUARIO,"");
    }

    fun setIdUsuario(Id : String){
        val editor = preferences.edit()
        editor.putString(PREFERENCES_ID_USUARIO, Id)
        editor.apply()
    }

    /*
    PARA PODER REALIZAR LA CONSULTA DE LOS DATOS SE INSTANCIA NUESTRA CLASE MyPreference en donde queremos usar las varaibles, de esta forma:

    val misPreferencias = MyPreferences(this)

    OJO: La linea anterior va dentro del metodo OnCreate()

    UNA VEZ YA INSTANCIADO NUESTRA CLASE MyPreference PODEMOS ACCEDER A LOS DATOS CON LOS METODOS DE ESTA CLASE, USANDO LA SIGUIENTE FORMA:

    misPreferencias.getCantidadPreguntas()

    TAMBIEN PUDIENDO ASIGNANDO A VARIABLES:

    val miVariable = misPreferencias.getCantidadPreguntas()

    OJO: Solo podemos usar los metodos que inicien con get ya que esos solo sirven para obtener, los metodos que inicen con set solo deben usarse en SettingActivity.
     */
}