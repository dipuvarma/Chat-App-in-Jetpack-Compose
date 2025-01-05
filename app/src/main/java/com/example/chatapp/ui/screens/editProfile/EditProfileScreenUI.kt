package com.example.chatapp.ui.screens.editProfile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapp.domain.model.Gender
import com.example.chatapp.domain.model.User
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
    userName: String,
    viewModel: EditProfileViewModel = viewModel(),
) {

    var gender by remember { mutableStateOf<Gender?>(null) }
    var genderError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = gender) {
        if(gender != null) genderError = false
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
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
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = userName,
                onValueChange = { },
                readOnly = true,
                label = { Text(text = "Name") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
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

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Gender",
                        fontSize = 16.sp
                    )
                    LabelledRadioButton(
                        label = "Male",
                        color = Color.Black,
                        selected = gender == Gender.Male,
                        onClick = { gender = Gender.Male }
                    )
                    LabelledRadioButton(
                        label = "Female",
                        selected = gender == Gender.Female,
                        onClick = { gender = Gender.Female },
                        color = Color.Black
                    )
                    if (genderError == true){
                        Text(text = "Required")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    if (
                        TextInputState.allHaveValidInputs(
                            bioInput
                        )
                    ) {
                        gender.let {
                            val user = User(
                                name = userName,
                                email = userEmail,
                                bio = bioInput.value(),
                                gender = it!!
                            )
                            viewModel.saveUser(user) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Your Profile Save Successfully")
                                }
                            }
                        }
                    }
                    if (gender == null){
                        genderError = true
                    }
                }
            ) {
                Text(
                    text = "Save"
                )
            }
        }
    }
}

