package com.peterica.rest.controller.v1.user.request

import com.peterica.domain.user.User


data class UserCreateRequest(
    val username: String,
    val email: String
) {
    fun toDomain(): User {
        return User(
            username = username,
            email = email
        )
    }
}
