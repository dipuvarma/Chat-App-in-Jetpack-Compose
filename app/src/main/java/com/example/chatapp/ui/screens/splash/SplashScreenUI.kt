package com.example.chatapp.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatapp.R
import com.example.chatapp.ui.theme.Gradient

@Composable
fun SplashScreenUI(
    viewModel: SplashViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.checkLoggedInStatus(navController)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gradient),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.icon_fg),
            contentDescription = "Chat Splash Icon"
        )
    }
}