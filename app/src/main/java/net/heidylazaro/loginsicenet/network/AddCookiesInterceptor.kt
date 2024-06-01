package net.heidylazaro.loginsicenet.network

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences may ary, but this will work 99% of the time.
 */
class AddCookiesInterceptor(// We're storing our stuff in a database made just for cookies called PREF_COOKIES.
    // I reccomend you do this, and don't change this default value.
    private val context: Context
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val preferences = PreferenceManager.getDefaultSharedPreferences(
            context
        ).getStringSet(PREF_COOKIES, HashSet()) as HashSet<String>?

        // Use the following if you need everything in one line.
        // Some APIs die if you do it differently.
        /*String cookiestring = "";
        for (String cookie : preferences) {
            String[] parser = cookie.split(";");
            cookiestring = cookiestring + parser[0] + "; ";
        }
        builder.addHeader("Cookie", cookiestring);
        */for (cookie in preferences!!) {
            builder.addHeader("Cookie", cookie)
            Log.d("cookie", cookie)
        }
        return chain.proceed(builder.build())
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }
}

//Cookies Interceptor para la clase
class CookiesInterceptor : Interceptor {

    // Variable que almacena las cookies
    private var cookies: List<String> = emptyList()

    // Método para establecer las cookies
    fun setCookies(cookies: List<String>) {
        this.cookies = cookies
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Agregar las cookies al encabezado de la solicitud
        if (cookies.isNotEmpty()) {
            val cookiesHeader = StringBuilder()
            for (cookie in cookies) {
                if (cookiesHeader.isNotEmpty()) {
                    cookiesHeader.append("; ")
                }
                cookiesHeader.append(cookie)
            }

            request = request.newBuilder()
                .header("Cookie", cookiesHeader.toString())
                .build()
        }

        val response = chain.proceed(request)

        // Almacenar las cookies de la respuesta para futuras solicitudes
        val receivedCookies = response.headers("Set-Cookie")
        if (receivedCookies.isNotEmpty()) {
            setCookies(receivedCookies)
        }

        return response
    }
}