package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import dev.tounyu.kmmphotos.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetLocalPhotoUseCase(
    private val localPhotoRepository: LocalPhotoRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : CoroutineUseCase<Unit, PhotoList>(dispatcher) {
    override suspend fun execute(param: Unit): PhotoList {
        return PhotoList(localPhotoRepository.getPhotos())
    }
}
