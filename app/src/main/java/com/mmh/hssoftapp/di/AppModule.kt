package com.mmh.hssoftapp.di

import android.content.Context
import androidx.room.Room
import com.mmh.hssoftapp.data.apis.CountryApi
import com.mmh.hssoftapp.data.database.CountryDatabase
import com.mmh.hssoftapp.data.repositories.CountryRepository
import com.mmh.hssoftapp.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

private const val BASE_URL: String = "https://countries.trevorblades.com/"
private const val DB_NAME = "countryDB"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCountryApi(): CountryApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build().create(CountryApi::class.java)

    @Singleton
    @Provides
    fun provideCountryRepository(
        @ApplicationContext context: Context,
        api: CountryApi,
        database: CountryDatabase
    ): CountryRepository = CountryRepository(context, api, database)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CountryDatabase = Room.databaseBuilder(
        context,
        CountryDatabase::class.java,
        DB_NAME)
        .build()
}