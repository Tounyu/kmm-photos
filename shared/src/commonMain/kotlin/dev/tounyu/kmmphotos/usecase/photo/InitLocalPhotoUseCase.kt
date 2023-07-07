package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

expect class InitLocalPhotoUseCase(
    localPhotoRepository: LocalPhotoRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
)
