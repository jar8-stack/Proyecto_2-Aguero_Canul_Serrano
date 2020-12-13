package com.example.proyecto_1_aguero_castillo_canul_serrano.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ConfigurationThemesDAO {

    @Query("INSERT INTO configuration_themes(id_configuration_theme,id_configuration,id_theme,status_configuration_themes) VALUES(null,:idConfiguration,:idTheme,:status)")
    fun insertConfigurationTheme(idConfiguration:Int,idTheme:Int, status:Boolean)

    @Query("SELECT status_configuration_themes FROM configuration_themes WHERE id_configuration = :idConfiguration AND id_theme = :idTema")
    fun traerStatusTema(idConfiguration: Int, idTema: Int) : Boolean

    @Query("UPDATE configuration_themes SET status_configuration_themes = :statusTema WHERE id_configuration = :idConfiguracion AND id_theme = :idTema")
    fun actualizarStatusTema(idTema:Int,idConfiguracion: Int,statusTema:Boolean)

}