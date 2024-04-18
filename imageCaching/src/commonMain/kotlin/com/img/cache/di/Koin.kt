package com.img.cache.di

import androidx.compose.ui.graphics.ImageBitmap
import com.img.cache.data.repo.My_CacheDataImp
import com.img.cache.data.repo.My_RemoteDataImp
import com.img.cache.domain.cache.AppLRUCache
import com.img.cache.domain.repo.My_ICacheData
import com.img.cache.domain.repo.My_IRemoteData
import com.img.cache.domain.repo.My_IRepository
import com.img.cache.domain.repo.My_RepositoryImp
import com.img.cache.domain.usecase.My_CacheImageUseCase
import com.img.cache.domain.usecase.My_RemoteImageUseCase
import com.img.cache.ui.vm.ImageRequestViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            useCasesModule,
            repositoryModule_3,
            ktorModule,
            dispatcherModule,
            lruCacheModule,
        )
    }

val viewModelModule = module {
    factory { ImageRequestViewModel(get()) }
}

val useCasesModule: Module = module {
    factory { My_RemoteImageUseCase(get(), get()) }
    factory { My_CacheImageUseCase(get(), get()) }
}


val repositoryModule_3 = module {
    single<My_IRemoteData> { My_RemoteDataImp(get(named("Hello"))) }
    single<My_ICacheData> { My_CacheDataImp(get(), get()) }
    single<My_IRepository> { My_RepositoryImp(get(), get()) }
}

val lruCacheModule = module {
    factory { AppLRUCache<String, ImageBitmap>(16) }
    single(named("Hello")) { "" }
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



fun initKoin() = initKoin {}