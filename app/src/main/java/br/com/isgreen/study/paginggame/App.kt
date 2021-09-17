package br.com.isgreen.study.paginggame

import android.app.Application
import br.com.isgreen.study.paginggame.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appComponent)
        }
    }
}