package com.example.chatapp.data

import com.example.chatapp.helper.DataStoreUtil

class LocalRepo(
    private val dataStore: DataStoreUtil
) {
    suspend fun onLoggedIn(){
        dataStore.setData("isLoggedIn", true)
    }

    suspend fun isLoggedIn(): Boolean {
        return dataStore.getData("isLoggedIn") ?: false
    }

}