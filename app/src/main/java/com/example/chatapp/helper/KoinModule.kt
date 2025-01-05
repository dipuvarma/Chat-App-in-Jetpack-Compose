package com.example.chatapp.helper

import com.example.chatapp.data.LocalRepo
import com.example.chatapp.data.UserRepo
import com.example.chatapp.ui.screens.editProfile.EditProfileViewModel
import com.example.chatapp.ui.screens.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { UserRepo() }
    single { LocalRepo(DataStoreUtil.create(get())) }
}

val viewModelModule = module{
    viewModel { EditProfileViewModel(get(), get()) }
    viewModel{ SplashViewModel(get()) }

}