package com.example.chatapp.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
object SplashScreen

@Serializable
object LoginScreen

@Serializable
data class EditProfileScreen(
    val email: String,
)
@Serializable
object HomeScreen