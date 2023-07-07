package dev.tounyu.kmmphotos.di

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import dev.tounyu.kmmphotos.repository.LocalPhotoRepositoryImpl
import dev.tounyu.kmmphotos.usecase.photo.AuthLocalPhotoUseCase
import dev.tounyu.kmmphotos.usecase.photo.GetLocalPhotoUseCase
import dev.tounyu.kmmphotos.usecase.photo.InitLocalPhotoUseCase
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import kotlin.reflect.KClass


val sharedModule = module {
    single<LocalPhotoRepository> { LocalPhotoRepositoryImpl() }
    single { AuthLocalPhotoUseCase(get()) }
    single { InitLocalPhotoUseCase(get()) }
    single { GetLocalPhotoUseCase(get()) }
}

fun <T> Koin.getDependency(clazz: KClass<*>): T {
    return get(clazz, null) { parametersOf(clazz.simpleName) } as T
}
