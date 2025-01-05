package com.example.chatapp.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
object SplashScreen

@Serializable
object LoginScreen

@Serializable
data class EditProfileScreen(
    val email: String,
    val name: String
)
@Serializable
object HomeScreen