package net.heidylazaro.loginsicenet.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalificacionesFinales(
    @SerialName("grupo")
    val grupo : String = "",
    @SerialName("materia")
    val materia : String = "",
    @SerialName("calif")
    val calif : Int = 0,
    @SerialName("acred")
    val acred : String = "",
    @SerialName("Observaciones")
    val observaciones : String = ""
)
