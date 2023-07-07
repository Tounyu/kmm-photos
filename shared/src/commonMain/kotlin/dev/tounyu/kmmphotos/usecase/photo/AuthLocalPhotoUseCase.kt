package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

expect class AuthLocalPhotoUseCase(
    initLocalPhotoUseCase: InitLocalPhotoUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : CoroutineUseCase<Unit, Boolean>