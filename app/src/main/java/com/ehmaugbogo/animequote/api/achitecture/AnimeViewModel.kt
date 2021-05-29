package com.ehmaugbogo.animequote.api.achitecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ehmaugbogo.animequote.api.models.PhotoUrl
import com.ehmaugbogo.animequote.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * @company ..: GIGM
 * @author ..: Ehma Ugbogo
 * @email ..: emmanuel.ugbogo@thegiggroupng.com
 * @created ..: 29-05-2021
 */




class AnimeViewModel: ViewModel() {
    private val repo get() = Repository
    init {

    }

    fun getPhotoByCategory(category: String, single: Boolean = true): LiveData<Result<PhotoUrl?>>{
        return liveData(Dispatchers.IO) {
            val response = repo.getPhotoByCategory(category, single)
            emit(response)
        }
    }


    fun getAllAvailableAnimes() = liveData(Dispatchers.IO) {
        val response = repo.getAllAvailableAnimes()
        emit(response)
    }

    fun getQuotesByAnimeTitle(title: String) = liveData(Dispatchers.IO) {
        val response = repo.getQuotesByAnimeTitle(title)
        emit(response)
    }

}