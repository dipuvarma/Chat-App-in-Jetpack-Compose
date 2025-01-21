package com.example.chatapp.presentation.screens.editProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatapp.domain.model.Gender
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.navigation.HomeScreen
import com.streamliners.compose.comp.select.RadioGroup
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs
import com.streamliners.compose.comp.textInput.state.value
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreenUI(
    userEmail: String,
    viewModel: EditProfileViewModel,
    navController: NavController
) {

    val gender = remember { mutableStateOf<Gender?>(null) }
    //val gender = MutableState<Gender?>(null)
    var genderError by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = gender) {
        if (gender != null) genderError = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->

        val nameInput = remember {
            mutableStateOf(
                TextInputState(
                    label = "Bio",
                    inputConfig = InputConfig.text {
                        optional = true
                        minLength = 3
                        maxLength = 20
                    }
                )
            )
        }
        val bioInput = remember {
            mutableStateOf(
                TextInputState(
                    label = "Bio",
                    inputConfig = InputConfig.text {
                        optional = true
                        minLength = 10
                        maxLength = 50
                    }
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextInputLayout(
                state = nameInput,
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = userEmail,
                onValueChange = { },
                readOnly = true,
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextInputLayout(state = bioInput)

            Spacer(modifier = Modifier.height(8.dp))

            Card {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                ) {
                    RadioGroup(
                        title = "Gender",
                        state = gender,
                        options = Gender.entries.toList(),
                        labelExtractor = { it.name }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    if (
                        TextInputState.allHaveValidInputs(
                            nameInput, bioInput
                        )
                    ) {
                        gender.value?.let {
                            val user = User(
                                name = nameInput.value(),
                                email = userEmail,
                                bio = bioInput.value(),
                                gender = it
                            )
                            viewModel.saveUser(user) {
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar("Your Profile Save Successfully")
                                }
                            }
                        }
                        if (gender == null) {
                            genderError = true
                        }
                        navController.navigate(HomeScreen)
                    }

                }
            ) {
                Text(text = "Save")
            }
        }
    }
}

