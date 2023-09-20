package com.example.rickandmortyapplication.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapplication.common.Constants
import com.example.rickandmortyapplication.data.local.CharacterDao
import com.example.rickandmortyapplication.data.local.CharacterDatabase
import com.example.rickandmortyapplication.data.remote.RickAndMortyApi
import com.example.rickandmortyapplication.data.repository.RickAndMortyRepositoryImpl
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    @Provides
    @Singleton
    fun provideRickAndMortyApi() : RickAndMortyApi {
        return Retrofit.Builder().
        baseUrl(Constants.BASE_URL).
        client(client).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(RickAndMortyApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRickAndMortyRepository(api : RickAndMortyApi) : RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context) : CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "character.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(characterDatabase : CharacterDatabase) : CharacterDao {
        return characterDatabase.characterDao()
    }

}