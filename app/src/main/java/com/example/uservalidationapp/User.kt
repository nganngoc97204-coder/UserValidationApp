package com.example.uservalidationapp

import java.io.Serializable

data class User(
    val fullName: String,
    val email: String,
    val phone: String,
    val password: String
) : Serializable