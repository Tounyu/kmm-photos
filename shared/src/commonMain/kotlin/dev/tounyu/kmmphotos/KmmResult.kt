package dev.tounyu.kmmphotos

sealed interface KmmResult<out T : Any> {
    data class Success<T : Any>(val data: T) : KmmResult<T>
    data class Error(val throwable: Throwable) : KmmResult<Nothing>
}
