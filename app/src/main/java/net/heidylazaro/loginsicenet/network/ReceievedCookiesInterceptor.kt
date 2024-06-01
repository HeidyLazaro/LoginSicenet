package net.heidylazaro.loginsicenet.network

import android.content.Context
import android.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

// Original written by tsuharesu
// Adapted to create a "drop it in and watch it work" approach by Nikhil Jha.
// Just add your package statement and drop it in the folder with all your other classes.


class ReceivedCookiesInterceptor // AddCookiesInterceptor()
    (private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies = PreferenceManager.getDefaultSharedPreferences(
                context
            ).getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>?
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies!!.add(header)
            }
            val memes = PreferenceManager.getDefaultSharedPreferences(
                context
            ).edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
        }
        return originalResponse
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }
}