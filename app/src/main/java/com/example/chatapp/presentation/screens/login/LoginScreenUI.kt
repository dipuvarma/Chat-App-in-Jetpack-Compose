package com.example.chatapp.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.chatapp.presentation.navigation.EditProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenUI(navController: NavHostController) {
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Welcome to Chat App") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            )
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            SignInWithGoogleButton(
                onSuccess = { user ->
                    Toast.makeText(context, "Sign-in as ${user.email}", Toast.LENGTH_SHORT).show()
                    navController.navigate(EditProfileScreen(email = "${user.email}", name = "${user.displayName}"))
                },
                onError = { user ->
                    Toast.makeText(context, "Error : ${user?.message}", Toast.LENGTH_SHORT).show()
                })
        }
    }
}