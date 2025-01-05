package com.example.chatapp.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chatapp.data.LocalRepo
import com.example.chatapp.ui.navigation.HomeScreen
import com.example.chatapp.ui.navigation.LoginScreen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val localRepo: LocalRepo
) : ViewModel() {

    fun checkLoggedInStatus(
        navController: NavController,
    ) {
        viewModelScope.launch {
            if (localRepo.isLoggedIn()) {
                navController.navigate(HomeScreen)
            } else {
                navController.navigate(LoginScreen)
            }
        }
    }

}