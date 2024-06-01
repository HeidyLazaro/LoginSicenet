package net.heidylazaro.loginsicenet.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kardex(
    @SerialName("FecEsp") val fecEsp: String? = null,
    @SerialName("ClvMat") val clvMat: String? = null,
    @SerialName("ClvOfiMat") val clvOfiMat: String? = null,
    @SerialName("Materia") val materia: String? = null,
    @SerialName("Cdts") val cdts: Int? = null,
    @SerialName("Calif") val calif: Int? = null,
    @SerialName("Acred") val acred: String? = null,
    @SerialName("S1") val s1: String? = null,
    @SerialName("P1") val p1: String? = null,
    @SerialName("A1") val a1: String? = null,
    @SerialName("S2") val s2: String? = null,
    @SerialName("P2") val p2: String? = null,
    @SerialName("A2") val a2: String? = null,
    @SerialName("S3") val s3: String? = null,
    @SerialName("P3") val p3: String? = null,
    @SerialName("A3") val a3: String? = null
)

@Serializable
data class KardexResponse(
    @SerialName("getAllKardexConPromedioByAlumnoResult")
    val result: KardexResult
)

@Serializable
data class KardexResult(
    @SerialName("lstKardex") val kardex: List<Kardex>,
    val Promedio: Promedio
)

@Serializable
data class Promedio(
    @SerialName("PromedioGral") val promedioGeneral: Double,
    @SerialName("CdtsAcum") val creditosAcumulados: Int,
    @SerialName("CdtsPlan") val creditosPlan: Int,
    @SerialName("MatCursadas") val materiasCursadas: Int,
    @SerialName("MatAprobadas") val materiasAprobadas: Int,
    @SerialName("AvanceCdts") val avanceCreditos: Double
)
