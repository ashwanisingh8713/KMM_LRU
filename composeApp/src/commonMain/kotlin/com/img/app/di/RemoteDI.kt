package com.img.app.di

import com.img.app.data.RemoteDataImp
import com.img.app.domain.repo.IRemoteRepository
import com.img.app.domain.usecase.RemoteDataUseCase
import com.img.app.ui.GalleryViewModel
import com.img.cache.di.dispatcherModule
import com.img.cache.di.ktorModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appDIModules: List<Module>
    get() = listOf(
        viewModelModule,
        repositoryModule,
        useCasesModule,
        appKtorModule,
        dispatcherModule,
        endPointModule
    )


val useCasesModule = module {
    factory{RemoteDataUseCase(get(), get())}
}
val viewModelModule = module {
    factory { GalleryViewModel(get()) }
}

val repositoryModule = module {
    single<IRemoteRepository> { RemoteDataImp(get(), get(named("UnSplashPhotosUrl"))) }
}

val appKtorModule = module {
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

val endPointModule = module{
    single(named("UnSplashPhotosUrl")) {"https://api.unsplash.com/photos/random?client_id=ef_rh-yN1hivXmrW-FUJb1PO4jNHMCdxYOFiH_khJTM&count=100" }
}