package com.peterica.domain.user


import org.springframework.stereotype.Service

@Service
class UserService(
    private val userReader: UserReader,
    private val userCreator: UserCreator
) {
    fun findById(id: Long): User {
        return userReader.findByIdOrNull(id)
            ?: throw Exception("user를 찾을 수 없음. id: $id")
        // TODO BadRequestException
    }

    fun createUser(user: User) {
        userCreator.createUser(user)
    }
}