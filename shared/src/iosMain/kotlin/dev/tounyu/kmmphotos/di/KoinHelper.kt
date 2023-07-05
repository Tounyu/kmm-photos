package dev.tounyu.kmmphotos.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(sharedModule)
}
