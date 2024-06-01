package net.heidylazaro.loginsicenet.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatosAlumno(
    @SerialName("fechaReins")
    var fechaReins: String,
    @SerialName("modEducativo")
    var modEducativo: Int,
    @SerialName("adeudo")
    var adeudo: Boolean,
    @SerialName("urlFoto")
    var urlFoto: String,
    @SerialName("adeudoDescripcion")
    var adeudoDescripcion: String,
    @SerialName("inscrito")
    var inscrito: Boolean,
    @SerialName("estatus")
    var estatus: String,
    @SerialName("semActual")
    var semActual: Int,
    @SerialName("cdtosAcumulados")
    var cdtosAcumulados: Int,
    @SerialName("cdtosActuales")
    var cdtosActuales: Int,
    @SerialName("especialidad")
    var especialidad: String,
    @SerialName("carrera")
    var carrera: String,
    @SerialName("lineamiento")
    var lineamiento: Int,
    @SerialName("nombre")
    var nombre: String,
    @SerialName("matricula")
    var matricula: String
)
