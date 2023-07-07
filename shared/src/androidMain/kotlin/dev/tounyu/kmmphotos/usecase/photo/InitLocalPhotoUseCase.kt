package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import dev.tounyu.kmmphotos.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

actual class InitLocalPhotoUseCase actual constructor(
    private val localPhotoRepository: LocalPhotoRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(param: Unit) {
        TODO("Not yet implemented")
    }
}
