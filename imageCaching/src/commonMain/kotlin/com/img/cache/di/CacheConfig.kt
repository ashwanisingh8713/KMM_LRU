package com.img.cache.di

import androidx.compose.ui.graphics.ImageBitmap
import com.img.cache.data.repo.ImgCacheDataImp
import com.img.cache.domain.cache.AppLRUCache
import com.img.cache.domain.repo.ICacheImg
import com.img.cache.domain.repo.ICacheImgRepository
import com.img.cache.domain.repo.CacheImgRepositoryImp
import com.img.cache.domain.usecase.My_CacheImageUseCase
import com.img.cache.ui.vm.ImageRequestState
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun setupCache(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            useCasesModule,
            repositoryModule,
            ktorModule,
            dispatcherModule,
            lruCacheModule,
        )
    }

fun cacheSetup(vararg module: Module ) = run {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCasesModule,
            repositoryModule,
            ktorModule,
            dispatcherModule,
            lruCacheModule,
        )
    )
}

internal val viewModelModule = module {
    factory { ImageRequestState(get()) }
}

internal val useCasesModule: Module = module {
    factory { My_CacheImageUseCase(get(), get()) }
}


internal val repositoryModule = module {
    single<ICacheImg> { ImgCacheDataImp(get(), get()) }
    single<ICacheImgRepository> { CacheImgRepositoryImp(get()) }
}

internal val lruCacheModule = module {
    factory { AppLRUCache<String, ImageBitmap>(16) }
}

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

}



val dispatcherModule = module {
    factory { Dispatchers.Default }
}
//fun setupCache() = setupCache {}