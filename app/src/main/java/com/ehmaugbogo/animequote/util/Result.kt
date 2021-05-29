package com.ehmaugbogo.animequote.util


/**
 * @company ..: GIGM
 * @author ..: Ehma Ugbogo
 * @email ..: emmanuel.ugbogo@thegiggroupng.com
 * @created ..: 29-05-2021
 */

sealed class Result<out T> {
    object Loading: Result<Nothing>()
    data class Success<T>(val data: T, val msg: String): Result<T>()
    data class Failure(val error: Throwable): Result<Nothing>()
}
