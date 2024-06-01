package net.heidylazaro.loginsicenet.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST



//compilador retrofit
/*private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(
    BASE_URL).build()*/
/*private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()*/

/*val requestBodyXMLText = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <soap:Body>\n" +
        "        <accesoLogin xmlns=\"http://tempuri.org/\">\n" +
        "            <strMatricula>s19120160</strMatricula>\n" +
        "            <strContrasenia>6Ed=/J8</strContrasenia>\n" +
        "            <tipoUsuario>ALUMNO</tipoUsuario>\n" +
        "        </accesoLogin>\n" +
        "    </soap:Body>\n" +
        "</soap:Envelope>"
 */
interface loginAccesoAlumno{
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: \"http://tempuri.org/accesoLogin\""
    )
    @POST("/ws/wsalumnos.asmx")
    suspend fun getAcceso(@Body requestBody: RequestBody) : ResponseBody

}
interface DatosAlumnoApi {
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: \"http://tempuri.org/getAlumnoAcademicoWithLineamiento\""
    )
    @POST("/ws/wsalumnos.asmx")
    suspend fun getAlumnoAcademicoWithLineamiento(@Body requestBody: RequestBody): ResponseBody
}

// Carga Academica
interface CargaAcademica{
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: \"http://tempuri.org/getCargaAcademicaByAlumno\""
    )
    @POST("/ws/wsalumnos.asmx")
    suspend fun getCargaAcademicaByAlumno(@Body requestBody: RequestBody) : ResponseBody
}
// Kardex

interface Kardex {
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: \"http://tempuri.org/getAllKardexConPromedioByAlumno\""
    )
    @POST("/ws/wsalumnos.asmx")
    suspend fun getAllKardexConPromedioByAlumno(@Body requestBody: RequestBody) : ResponseBody
}
// Calificación Unidad
interface CalificacionesUnidad {
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: \"http://tempuri.org/getCalifUnidadesByAlumno\""
    )
    @POST("/ws/wsalumnos.asmx")
    suspend fun getCalifUnidadesByAlumno(@Body requestBody: RequestBody) : ResponseBody
}
// Calificacion Final
interface CalificacionesFinales {
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: \"http://tempuri.org/getAllCalifFinalByAlumnos\""
    )
    @POST("/ws/wsalumnos.asmx")
    suspend fun getAllCalifFinalByAlumnos(@Body requestBody: RequestBody) : ResponseBody
}
/*object SicenetApi{
    val retrofitService: loginAccesoAlumno by lazy {
        retrofit.create(loginAccesoAlumno::class.java)
    }
}*/

