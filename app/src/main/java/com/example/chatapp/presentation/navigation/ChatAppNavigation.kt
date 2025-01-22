package com.example.chatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.chatapp.presentation.screens.home.HomeScreenUI
import com.example.chatapp.presentation.screens.editProfile.EditProfileScreenUI
import com.example.chatapp.presentation.screens.login.LoginScreenUI
import com.example.chatapp.presentation.screens.splash.SplashScreenUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatAppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen
    ) {
        composable<SplashScreen> {
            SplashScreenUI(
                navController = navController,
                viewModel = koinViewModel()
            )
        }
        composable<LoginScreen> {
            LoginScreenUI(
                navController,
                koinViewModel()
            )
        }
        composable<EditProfileScreen> {
            val user = it.toRoute<EditProfileScreen>()
            EditProfileScreenUI(
                viewModel = koinViewModel(),
                userEmail = user.email,
                navController = navController
            )
        }
        composable<HomeScreen> {
            HomeScreenUI()
        }
    }
}
