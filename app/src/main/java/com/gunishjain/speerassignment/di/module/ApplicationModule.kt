package com.gunishjain.speerassignment.di.module

import com.gunishjain.speerassignment.data.api.NetworkService
import com.gunishjain.speerassignment.di.BaseUrl
import com.gunishjain.speerassignment.utils.AppConstant
import com.gunishjain.speerassignment.utils.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstant.BASE_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCacheInterceptor(): CacheInterceptor =
        CacheInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(cacheInterceptor: CacheInterceptor): OkHttpClient {
        val cacheLoc = File("/var/tmp/okhttp")
        return OkHttpClient().newBuilder()
            .cache(Cache(File(cacheLoc, "http-cache"), 10L * 1024L * 1024L))
            .addNetworkInterceptor(cacheInterceptor).build()
    }


    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }


}