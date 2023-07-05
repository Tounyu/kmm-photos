package dev.tounyu.kmmphotos.di

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import dev.tounyu.kmmphotos.repository.LocalPhotoRepositoryImpl
import dev.tounyu.kmmphotos.usecase.photo.AuthLocalPhotoUseCase
import dev.tounyu.kmmphotos.usecase.photo.LoadLocalPhotoUseCase
import org.koin.dsl.module


val appModule = module {
    single<LocalPhotoRepository> { LocalPhotoRepositoryImpl() }
    single { AuthLocalPhotoUseCase() }
    single { LoadLocalPhotoUseCase(get()) }
}
