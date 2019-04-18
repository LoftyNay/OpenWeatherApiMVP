package com.ltn.openweather.dagger.module

import com.ltn.openweather.Constants
import com.ltn.openweather.network.ApiOpenWeather
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): ApiOpenWeather {
        return retrofit.create(ApiOpenWeather::class.java)
    }
}