package com.ehmaugbogo.animequote.api

import com.ehmaugbogo.animequote.util.Constants.ANIME_BASE_URL
import com.ehmaugbogo.animequote.util.Constants.WAIFU_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @company ..: GIGM
 * @author ..: Ehma Ugbogo
 * @email ..: emmanuel.ugbogo@thegiggroupng.com
 * @created ..: 29-05-2021
 */

private const val REQUEST_TIMEOUT = 60L

object RetrofitClient {

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .baseUrl(baseUrl)
            .build()
    }

    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    fun getAmineQuoteApi(): AnimeQuoteApi{
        return getRetrofit(ANIME_BASE_URL).create(AnimeQuoteApi::class.java)
    }

    fun getPhotoApi(): WaifuApi{
        return getRetrofit(WAIFU_BASE_URL).create(WaifuApi::class.java)
    }


}