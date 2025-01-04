package com.example.chatapp.domain.model

data class User(

    val id: String? = null,
    val name: String,
    val email: String,
    val bio: String,
    val gender: Gender
)
