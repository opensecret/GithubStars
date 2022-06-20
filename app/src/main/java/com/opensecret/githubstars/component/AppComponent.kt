package com.opensecret.githubstars.component

import android.app.Application
import com.opensecret.githubstars.GithubApplication
import com.opensecret.githubstars.factory.ViewModelFactory
import com.opensecret.githubstars.module.*
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class,
    RetrofitModule::class,
    ViewModelModule::class
])
@Singleton
interface AppComponent : AndroidInjector<GithubApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}