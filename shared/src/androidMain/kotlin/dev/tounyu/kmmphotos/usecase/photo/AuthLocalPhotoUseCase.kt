package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

actual class AuthLocalPhotoUseCase actual constructor(
    dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(param: Unit): Boolean {
        TODO("Not yet implemented")
    }
}