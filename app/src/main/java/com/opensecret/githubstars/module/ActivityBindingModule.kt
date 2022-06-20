package com.opensecret.githubstars.module

import com.opensecret.githubstars.ui.LocalFragment
import com.opensecret.githubstars.ui.MainActivity
import com.opensecret.githubstars.ui.RemoteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun remoteFragment(): RemoteFragment

    @ContributesAndroidInjector
    abstract fun localFragment(): LocalFragment
}