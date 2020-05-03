package pl.srw.movies.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pl.srw.movies.BuildConfig
import pl.srw.movies.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

val networkModule = module {
    single { provideRetrofit(get()) }
    factory { provideOkHttp(get(), get()) }
    factory { provideHttpLoggingInterceptor() }
    factory { ApiKeyInterceptor() }
}

private fun provideRetrofit(okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(Config.baseUrl)
        .client(okHttpClient)
        .build()

private fun provideOkHttp(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiKeyInterceptor: ApiKeyInterceptor
) = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .addInterceptor(apiKeyInterceptor)
    .build()

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) = Timber.i(message)
    }
    val interceptor = HttpLoggingInterceptor(logger)
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

private class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}