package com.peterica.domain.user

interface UserRepository {
    fun findByIdOrNull(id: Long): User?

    fun create(user: User)
}