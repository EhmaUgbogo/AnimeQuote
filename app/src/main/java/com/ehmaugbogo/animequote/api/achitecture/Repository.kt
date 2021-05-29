package com.ehmaugbogo.animequote.api.achitecture

import com.ehmaugbogo.animequote.api.RetrofitClient
import com.ehmaugbogo.animequote.api.models.AnimeQuote
import com.ehmaugbogo.animequote.api.models.PhotoUrl
import com.ehmaugbogo.animequote.util.Result


/**
 * @company ..: GIGM
 * @author ..: Ehma Ugbogo
 * @email ..: emmanuel.ugbogo@thegiggroupng.com
 * @created ..: 29-05-2021
 */

object Repository {
    private val animeQuoteApi = RetrofitClient.getAmineQuoteApi()
    private val photoApi = RetrofitClient.getPhotoApi()

    suspend fun getPhotoByCategory(category: String, single: Boolean = true): Result<PhotoUrl?> {
        return try {
            val response = photoApi.getPhotoByCategory(category)
            Result.Success(response, "Photo url fetched successfully")
        } catch (e: Throwable){
            Result.Failure(e)
        }
    }

    suspend fun getAllAvailableAnimes(): Result<List<String>?> {
        return try {
            val response = animeQuoteApi.getAllAvailableAnimes()
            Result.Success(response, "Available anime(s) fetched successfully")
        } catch (e: Throwable){
            Result.Failure(e)
        }
    }

    suspend fun getQuotesByAnimeTitle(title: String): Result<List<AnimeQuote>?> {
        return try {
            val response = animeQuoteApi.getQuotesByAnimeTitle(title)
            Result.Success(response, "Available anime(s) fetched successfully")
        } catch (e: Throwable){
            Result.Failure(e)
        }
    }

}