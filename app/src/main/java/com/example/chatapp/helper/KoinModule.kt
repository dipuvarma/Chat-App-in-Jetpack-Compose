package com.example.chatapp.helper

import com.example.chatapp.data.LocalRepo
import com.example.chatapp.data.remote.UserRepo
import com.example.chatapp.presentation.screens.editProfile.EditProfileViewModel
import com.example.chatapp.presentation.screens.login.LoginViewModel
import com.example.chatapp.presentation.screens.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { UserRepo() }
    single { LocalRepo(DataStoreUtil.create(get())) }
}

val viewModelModule = module {
    viewModel { EditProfileViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }

}