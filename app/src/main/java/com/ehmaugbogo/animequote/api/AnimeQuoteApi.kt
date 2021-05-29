package com.ehmaugbogo.animequote.api

import com.ehmaugbogo.animequote.api.models.AnimeQuote
import com.ehmaugbogo.animequote.api.models.PhotoUrl
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * @company ..: GIGM
 * @author ..: Ehma Ugbogo
 * @email ..: emmanuel.ugbogo@thegiggroupng.com
 * @created ..: 29-05-2021
 */

private const val type = "type"
interface AnimeQuoteApi {

    @GET("available/anime")
    suspend fun getAllAvailableAnimes(): List<String>

    @GET("quotes/anime")
    suspend fun getQuotesByAnimeTitle(@Query("title") title: String): List<AnimeQuote>

}

interface WaifuApi{
    @GET("$type/{category}")
    suspend fun getPhotoByCategory(@Path("category") category: String): PhotoUrl
}