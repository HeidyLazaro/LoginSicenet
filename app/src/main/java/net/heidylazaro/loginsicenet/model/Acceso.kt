package net.heidylazaro.loginsicenet.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Acceso(
    @SerialName("acceso")
    val acceso : Boolean = false,
    @SerialName("estatus")
    val estatus : String = "",
    @SerialName("tipoUsuario")
    val tipoUsuario : Int = 0,
    @SerialName("contrasenia")
    val contrasenia : String = "",
    @SerialName("matricula")
    val matricula : String = ""
)
