package net.heidylazaro.loginsicenet.model.nube

data class CargaSicenet(
    val Semipresencial: String,
    val Observaciones: String,
    val Docente: String,
    val clvOficial: String,
    val Sabado: String,
    val Viernes: String,
    val Jueves: String,
    val Miercoles: String,
    val Martes: String,
    val Lunes: String,
    val EstadoMateria: String,
    val CreditosMateria: Int,
    val Materia: String,
    val Grupo: String
)
