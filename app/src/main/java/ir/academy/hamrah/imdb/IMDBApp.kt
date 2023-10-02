package ir.academy.hamrah.imdb

import android.app.Application
import ir.academy.hamrah.imdb.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IMDBApp: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin(appDeclaration = {
            androidContext(this@IMDBApp)
            modules(myModules)
        })
    }
}