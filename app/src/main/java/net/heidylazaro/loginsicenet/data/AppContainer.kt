package net.heidylazaro.loginsicenet.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import net.heidylazaro.loginsicenet.network.AddCookiesInterceptor
import net.heidylazaro.loginsicenet.network.ReceivedCookiesInterceptor
import net.heidylazaro.loginsicenet.network.CalificacionesFinales
import net.heidylazaro.loginsicenet.network.CalificacionesUnidad
import net.heidylazaro.loginsicenet.network.CargaAcademica
import net.heidylazaro.loginsicenet.network.DatosAlumnoApi
import net.heidylazaro.loginsicenet.network.Kardex
import net.heidylazaro.loginsicenet.network.loginAccesoAlumno
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val sicenetRepo : LoginSicenetRepository
}

//Url
private const val BASE_URL = "https://sicenet.surguanajuato.tecnm.mx"
class DefaultContainer(val applicationContext: Context): AppContainer{

    private val baseUrl = "https://sicenet.surguanajuato.tecnm.mx"

    private var clienteOKHttp : OkHttpClient
    var matricula: String? = null

    init {
        clienteOKHttp = OkHttpClient()
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(AddCookiesInterceptor(applicationContext))
        builder.addInterceptor(ReceivedCookiesInterceptor(applicationContext))
        clienteOKHttp = builder.build()
    }
    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .client(clienteOKHttp)
        .build()

    private val accesoAlumno: loginAccesoAlumno by lazy {
        retrofit.create(loginAccesoAlumno::class.java)
    }

        private val retrofitDatosAlumno: DatosAlumnoApi by lazy {
            retrofit.create(DatosAlumnoApi::class.java)
        }
        private val retrofitCargaAcademica: CargaAcademica by lazy {
            retrofit.create(CargaAcademica::class.java)
        }
        private val retrofitKardexApi: Kardex by lazy {
            retrofit.create(Kardex::class.java)
        }
        private val retrofitCalificacionesUnidadApi: CalificacionesUnidad by lazy {
            retrofit.create(CalificacionesUnidad::class.java)
        }
        private val retrofitCalificacionesFinalesApi: CalificacionesFinales by lazy {
            retrofit.create(CalificacionesFinales::class.java)
        }

    override val sicenetRepo: LoginSicenetRepository by lazy {
        LoginSicenetRepository(
            accesoAlumno,
            retrofitDatosAlumno,
            retrofitCargaAcademica,
            retrofitKardexApi,
            retrofitCalificacionesUnidadApi,
            retrofitCalificacionesFinalesApi
        )
    }

        val sicenetRepoLocal: LoginSicenetRepository by lazy {
            LoginSicenetRepository(
                accesoAlumno,
                retrofitDatosAlumno,
                retrofitCargaAcademica,
                retrofitKardexApi,
                retrofitCalificacionesUnidadApi,
                retrofitCalificacionesFinalesApi
            )
        }
}



