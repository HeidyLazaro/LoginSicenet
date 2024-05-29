package net.heidylazaro.loginsicenet.model.nube

import kotlinx.serialization.Serializable

@Serializable
data class LoginSicenet(
    val acceso: String,
    val estatus: String,
    val tipoUsuario: String,
    val contrasenia: String,
    val matricula: String,
    val cookieSession: String
)
