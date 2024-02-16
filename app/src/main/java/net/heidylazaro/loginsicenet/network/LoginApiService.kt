package net.heidylazaro.loginsicenet.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.POST

//constante para conectar al servicio
private const val BASE_URL = "https://sicenet.surguanajuato.tecnm.mx"

//compilador retrofit
private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(
    BASE_URL).build()

val requestBodyXMLText = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <soap:Body>\n" +
        "        <accesoLogin xmlns=\"http://tempuri.org/\">\n" +
        "            <strMatricula>s19120160</strMatricula>\n" +
        "            <strContrasenia>6Ed=/J8</strContrasenia>\n" +
        "            <tipoUsuario>ALUMNO</tipoUsuario>\n" +
        "        </accesoLogin>\n" +
        "    </soap:Body>\n" +
        "</soap:Envelope>"
interface LoginApiService{
    /*@GET("wsalumnos.asmx")
    suspend fun getAlumnos(): String*/

    @GET("ws/wsalumnos.asmx")
    suspend fun obtenerDatosXML(): ResponseBody

}

object SicenetApi{
    val retrofitService: LoginApiService by lazy { retrofit.create(LoginApiService::class.java) }
}

