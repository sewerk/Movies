package pl.srw.movies

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.srw.movies.di.mainModule

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@MoviesApp)
            modules(mainModule)
        }
    }
}