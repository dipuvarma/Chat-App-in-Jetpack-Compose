package com.example.chatapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chatapp.data.LocalRepo
import com.example.chatapp.data.remote.UserRepo
import com.example.chatapp.presentation.navigation.EditProfileScreen
import com.example.chatapp.presentation.navigation.HomeScreen
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val localRepo: LocalRepo,
) : ViewModel() {

    fun onLoggedIn(email: String, navController: NavController) {
        viewModelScope.launch {
            val user = userRepo.getUserWithEmail(email)
            if (user != null) {
                localRepo.onLoggedIn()
                navController.navigate(HomeScreen)
            } else {
                navController.navigate(EditProfileScreen(email = email))
            }
        }
    }

}