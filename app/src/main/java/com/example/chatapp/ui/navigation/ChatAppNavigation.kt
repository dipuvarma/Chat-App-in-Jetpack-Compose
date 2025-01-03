package com.example.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            LoginScreenUI()
        }
        composable<EditProfileScreen> {
            EditProfileScreenUI()
        }
    }
}
