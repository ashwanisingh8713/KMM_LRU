package com.img.cache.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Ashwani on 14 April 2024
 */

abstract class MyBaseUseCase<in Param, out T> (val dispatcher: CoroutineDispatcher) {

    abstract suspend fun run(param: Param):T

    suspend operator fun invoke(param: Param):Result<T> = withContext(dispatcher) {
        try {
            Result.success(run(param))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}