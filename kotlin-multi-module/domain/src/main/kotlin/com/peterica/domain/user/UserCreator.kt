package com.peterica.domain.user

import org.springframework.stereotype.Component

@Component
class UserCreator(
    private val userRepository: UserRepository
) {
    fun createUser(user: User) {
        userRepository.create(user)
    }
}