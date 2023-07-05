package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import platform.Photos.PHAccessLevelReadWrite
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusLimited
import platform.Photos.PHPhotoLibrary
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class AuthLocalPhotoUseCase actual constructor(
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, Boolean>(dispatcher) {
    override suspend fun execute(param: Unit): Boolean {
        return requestAuthorization()
    }

    private suspend fun requestAuthorization() = suspendCoroutine {
        PHPhotoLibrary.requestAuthorizationForAccessLevel(PHAccessLevelReadWrite) { status ->
            val isAuthorized =
                (status == PHAuthorizationStatusAuthorized || status == PHAuthorizationStatusLimited)
            it.resume(isAuthorized)
        }
    }
}
