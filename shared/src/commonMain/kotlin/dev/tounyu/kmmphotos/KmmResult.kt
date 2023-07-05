package dev.tounyu.kmmphotos

sealed interface KmmResult<out T> {
    data class Success<T>(val data: T) : KmmResult<T>
    data class Error(val throwable: Throwable) : KmmResult<Nothing>
}
