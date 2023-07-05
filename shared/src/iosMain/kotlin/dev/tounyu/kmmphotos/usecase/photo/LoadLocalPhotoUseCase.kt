package dev.tounyu.kmmphotos.usecase.photo

import dev.tounyu.kmmphotos.repository.LocalPhotoRepository
import dev.tounyu.kmmphotos.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import platform.Foundation.NSComparisonPredicate.Companion.predicateWithFormat
import platform.Foundation.NSData
import platform.Foundation.NSSortDescriptor
import platform.ImageIO.CGImagePropertyOrientation
import platform.Photos.PHAsset
import platform.Photos.PHAssetMediaTypeImage
import platform.Photos.PHFetchOptions
import platform.Photos.PHImageManager
import platform.Photos.PHImageRequestOptions
import platform.Photos.PHImageRequestOptionsDeliveryModeOpportunistic
import platform.Photos.PHImageRequestOptionsResizeModeExact
import platform.UIKit.UIImage
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class LoadLocalPhotoUseCase actual constructor(
    private val localPhotoRepository: LocalPhotoRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(param: Unit) {
        val assets = fetchAssets()
        val images = requestImage(assets)
        localPhotoRepository.addPhotos(images.map { Photo(it) })
    }

    private val fetchOptions = PHFetchOptions().apply {
        predicate = predicateWithFormat(
            predicateFormat = "mediaType == %d",
            PHAssetMediaTypeImage,
        )
        sortDescriptors = listOf(NSSortDescriptor("creationDate", false))
    }

    private suspend fun fetchAssets() = suspendCoroutine {
        val result = PHAsset.fetchAssetsWithOptions(fetchOptions)
        val assets = mutableListOf<PHAsset>()
        result.enumerateObjectsUsingBlock { asset, _, _ ->
            (asset as? PHAsset)?.let {
                assets.add(asset)
            }
        }
        it.resume(assets)
    }

    private val requestImageOptions = PHImageRequestOptions().apply {
        resizeMode = PHImageRequestOptionsResizeModeExact
        deliveryMode = PHImageRequestOptionsDeliveryModeOpportunistic
        setSynchronous(true)
    }

    private suspend fun requestImage(assets: List<PHAsset>) = suspendCoroutine {
        val images = mutableListOf<UIImage>()
        assets.forEach {
            PHImageManager.defaultManager().requestImageDataAndOrientationForAsset(
                it,
                requestImageOptions
            ) { nsData: NSData?, s: String?, uInt: CGImagePropertyOrientation, map: Map<Any?, *>? ->
                nsData?.let {
                    images.add(UIImage(nsData))
                }
            }
        }
        it.resume(images)
    }
}
