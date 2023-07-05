package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

expect class LoadLocalPhotoUseCase(
    localPhotoRepository: LocalPhotoRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
)
