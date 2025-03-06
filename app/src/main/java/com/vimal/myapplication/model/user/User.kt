package com.vimal.myapplication.model.user

data class User(
    val address: Address?,
    val company: Company?,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)