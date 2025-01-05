package com.example.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.chatapp.ui.home.HomeScreenUI
import com.example.chatapp.ui.screens.editProfile.EditProfileScreenUI
import com.example.chatapp.ui.screens.login.LoginScreenUI
import com.example.chatapp.ui.screens.splash.SplashScreenUI
import kotlinx.coroutines.delay
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
            LoginScreenUI(navController)
        }
        composable<EditProfileScreen> {
            val user = it.toRoute<EditProfileScreen>()
            EditProfileScreenUI(
               viewModel =  koinViewModel(),
                userEmail = user.email,
               userName =  user.name,
                navController = navController
            )
        }
        composable<HomeScreen>{
            HomeScreenUI()
        }
    }
}
