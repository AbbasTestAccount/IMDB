package ir.academy.hamrah.imdb.di


import ir.academy.hamrah.imdb.model.net.createApiService
import org.koin.dsl.module

val myModules = module {
    single { createApiService() }
}