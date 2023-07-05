package dev.tounyu.kmmphotos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform