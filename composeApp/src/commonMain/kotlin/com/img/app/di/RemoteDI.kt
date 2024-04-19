package com.img.app.di

import com.img.app.data.RemoteDataImp
import com.img.app.domain.repo.IRemoteRepository
import com.img.app.domain.usecase.RemoteDataUseCase
import com.img.app.ui.GalleryViewModel
import com.img.cache.di.dispatcherModule
import com.img.cache.di.ktorModule
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appDIModules: List<Module>
    get() = listOf(
        viewModelModule,
        repositoryModule,
        useCasesModule,
        ktorModule,
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
    single<IRemoteRepository> { RemoteDataImp(get(), get()) }
}

val endPointModule = module{
    single(named("UnSplashPhotosUrl")) {"http://api.unsplash.com/photos/random?count=1000&client_id=ef_rh-yN1hivXmrW-FUJb1PO4jNHMCdxYOFiH_khJTM" }
}