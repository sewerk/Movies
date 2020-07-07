package pl.srw.movies

import android.app.Application
import android.os.StrictMode
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.srw.movies.di.networkModule
import pl.srw.movies.list.di.listModule
import timber.log.Timber

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            StrictMode.enableDefaults()
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build()
            )
        }

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@MoviesApp)
            modules(networkModule, listModule)
        }
    }
}