package com.example.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.chatapp.ui.screens.editProfile.EditProfileScreenUI
import com.example.chatapp.ui.screens.login.LoginScreenUI
import com.example.chatapp.ui.screens.splash.SplashScreenUI

@Composable
fun ChatAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginScreen
    ) {
        composable<SplashScreen> {
            SplashScreenUI()
        }
        composable<LoginScreen> {
            LoginScreenUI(navController)
        }
        composable<EditProfileScreen> {
            val user = it.toRoute<EditProfileScreen>()
            EditProfileScreenUI(user.email, user.name)
        }
    }
}
