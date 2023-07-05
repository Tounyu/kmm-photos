package dev.tounyu.kmmphotos.repository

import dev.tounyu.kmmphotos.usecase.photo.Photo

class LocalPhotoRepositoryImpl : LocalPhotoRepository {
    private val _photos = mutableListOf<Photo>()

    override suspend fun getPhotos(): List<Photo> {
        return _photos.toList()
    }

    override suspend fun addPhotos(photos: List<Photo>) {
        _photos.addAll(photos)
    }
}
