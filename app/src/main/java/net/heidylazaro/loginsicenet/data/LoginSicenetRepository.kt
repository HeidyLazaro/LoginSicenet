package net.heidylazaro.loginsicenet.data

import okhttp3.ResponseBody

interface LoginSicenetRepository {
    suspend fun getAcceso(): ResponseBody
}