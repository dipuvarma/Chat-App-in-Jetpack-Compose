package com.example.chatapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseCollection{

    fun FirebaseFirestore.userCollection() = collection("users")
}