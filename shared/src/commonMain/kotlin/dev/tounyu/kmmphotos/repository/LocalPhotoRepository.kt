package dev.tounyu.kmmphotos.repository

import dev.tounyu.kmmphotos.usecase.photo.Photo

interface LocalPhotoRepository {
    suspend fun getPhotos(): List<Photo>
    suspend fun addPhotos(photos: List<Photo>)
}
