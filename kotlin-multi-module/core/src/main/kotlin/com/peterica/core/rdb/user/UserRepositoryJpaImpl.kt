package com.peterica.core.rdb.user

import com.peterica.domain.user.User
import com.peterica.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class UserRepositoryJpaImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun findByIdOrNull(id: Long): User? {
        return userJpaRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun create(user: User) {
        userJpaRepository.save(UserEntity.fromDomain(user))
    }
}