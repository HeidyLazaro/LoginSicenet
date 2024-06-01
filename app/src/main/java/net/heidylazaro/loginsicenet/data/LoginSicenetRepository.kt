package net.heidylazaro.loginsicenet.data

import android.util.Log
import net.heidylazaro.loginsicenet.model.AccesoAlumnoEnvelope
import net.heidylazaro.loginsicenet.model.CalificacionesFinalesEnvelope
import net.heidylazaro.loginsicenet.model.CalificacionesUnidadEnvelope
import net.heidylazaro.loginsicenet.model.CargaAcademicaEnvelope
import net.heidylazaro.loginsicenet.model.DatosAlumnoEnvelope
import net.heidylazaro.loginsicenet.model.KardexEnvelope
import net.heidylazaro.loginsicenet.network.CalificacionesFinales
import net.heidylazaro.loginsicenet.network.CalificacionesUnidad
import net.heidylazaro.loginsicenet.network.CargaAcademica
import net.heidylazaro.loginsicenet.model.DatosAlumno
import net.heidylazaro.loginsicenet.network.DatosAlumnoApi
import net.heidylazaro.loginsicenet.network.Kardex

import net.heidylazaro.loginsicenet.network.loginAccesoAlumno
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.StringReader


private const val TAG_SUCCESS = "SUCCESS"
private const val TAG_ERROR = "ERROR"
class LoginSicenetRepository(
    private val accesoAlumno: loginAccesoAlumno,
    private val datosAlumno: DatosAlumnoApi,
    private val cargaAcademica: CargaAcademica,
    private val kardex: Kardex,
    private val calificacionesUnidad: CalificacionesUnidad,
    private val calificacionesFinales: CalificacionesFinales,
) {

    suspend fun getAcceso(matricula: String, contrasenia: String, tipoUsuario: String): String? {
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${matricula}</strMatricula>
                  <strContrasenia>${contrasenia}</strContrasenia>
                  <tipoUsuario>${tipoUsuario}</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = accesoAlumno.getAcceso(requestBody)
            val responseBodyString = response.string()

            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(AccesoAlumnoEnvelope::class.java, reader)
            var respuestaJson = envelope.body?.response?.result.toString()

            // Utiliza Gson para convertir el JSON a un objeto Kotlin
            respuestaJson
        } catch (e: IOException){
            Log.e(TAG_ERROR,"${e.message}")
            ""
        }
    }

    suspend fun getAlumno(): String? {
        val xml =
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <getAlumnoAcademicoWithLineamiento xmlns=\"http://tempuri.org/\" />\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>".trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = datosAlumno.getAlumnoAcademicoWithLineamiento(requestBody)
            val responseBodyString = response.string()
            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(DatosAlumnoEnvelope::class.java, reader)

            val jsonString = envelope.body?.response?.result.toString()
            jsonString
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }
    suspend fun getCargaAcademica(): String?{
        val xml = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getCargaAcademicaByAlumno xmlns=\"http://tempuri.org/\" />\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>".trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = cargaAcademica.getCargaAcademicaByAlumno(requestBody)
            val responseBodyString = response.string()
            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(CargaAcademicaEnvelope::class.java, reader)

            val respuestaJson = envelope.body?.response?.result.toString()
            respuestaJson
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }
    suspend fun getKardex(lineamiento: Int): String?{
        val xml = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getAllKardexConPromedioByAlumno xmlns=\"http://tempuri.org/\">\n" +
                "      <aluLineamiento>${lineamiento}</aluLineamiento>\n" +
                "    </getAllKardexConPromedioByAlumno>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>".trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = kardex.getAllKardexConPromedioByAlumno(requestBody)
            val responseBodyString = response.string()
            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(KardexEnvelope::class.java, reader)

            val respuestaJson = envelope.body?.response?.result.toString()
            respuestaJson
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }
    suspend fun getCalificacionesUnidades():String?{
        val xml = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getCalifUnidadesByAlumno xmlns=\"http://tempuri.org/\" />\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>".trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = calificacionesUnidad.getCalifUnidadesByAlumno(requestBody)
            val responseBodyString = response.string()
            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(CalificacionesUnidadEnvelope::class.java, reader)

            val respuestaJson = envelope.body?.response?.result.toString()
            Log.d(TAG_SUCCESS, respuestaJson)
            respuestaJson
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }
    suspend fun getCalificacionesFinales(modeloEducativo: Int):String?{
        val xml = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getAllCalifFinalByAlumnos xmlns=\"http://tempuri.org/\">\n" +
                "      <bytModEducativo>${modeloEducativo}</bytModEducativo>\n" +
                "    </getAllCalifFinalByAlumnos>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>".trimIndent()
        val requestBody= xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = calificacionesFinales.getAllCalifFinalByAlumnos(requestBody)
            val responseBodyString = response.string()
            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(CalificacionesFinalesEnvelope::class.java, reader)

            val respuestaJson = envelope.body?.response?.result.toString()
            respuestaJson
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }

}
