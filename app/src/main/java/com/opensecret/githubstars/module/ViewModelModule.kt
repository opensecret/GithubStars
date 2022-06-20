package com.opensecret.githubstars.module

import androidx.lifecycle.ViewModel
import com.opensecret.githubstars.factory.ViewModelKey
import com.opensecret.githubstars.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel
}