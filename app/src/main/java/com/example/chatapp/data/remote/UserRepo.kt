package com.example.chatapp.data.remote

import com.example.chatapp.data.remote.FirebaseCollection.userCollection
import com.example.chatapp.domain.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepo {

    suspend fun saveUser(user: User) {
        //save user to database
        Firebase.firestore
            .userCollection()
            .add(user)
            .await()
    }

    suspend fun getUserWithEmail(email: String): User? {
        return Firebase.firestore
            .userCollection()
            .whereEqualTo("email", email)
            .get()
            .await()
            .toObjects(User::class.java)
            .firstOrNull()
    }

}