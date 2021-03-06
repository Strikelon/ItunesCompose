package com.example.itunescompose.di

import com.example.itunescompose.BuildConfig
import com.example.itunescompose.data.network.NetworkApi
import com.example.itunescompose.data.network.interceptor.AcceptInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        acceptInterceptor: AcceptInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        okHttpClientBuilder
            .addInterceptor(acceptInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        moshi: Moshi,
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.API_BASE_URL)

    @Singleton
    @Provides
    fun providePublicRetrofit(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit = retrofitBuilder
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideNetworkApi(
        retrofit: Retrofit,
    ): NetworkApi = retrofit
        .create(NetworkApi::class.java)

    companion object {
        private const val TIMEOUT = 60L
    }
}
