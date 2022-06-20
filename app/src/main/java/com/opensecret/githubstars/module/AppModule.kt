package com.opensecret.githubstars.module

import android.app.Application
import android.content.Context
import com.opensecret.githubstars.dao.UserDao
import com.opensecret.githubstars.database.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideUserDatabase(context: Context): UserDatabase {
        return UserDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }
}