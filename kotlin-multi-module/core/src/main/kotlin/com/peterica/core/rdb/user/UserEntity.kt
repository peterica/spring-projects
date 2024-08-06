package com.peterica.core.rdb.user

import com.peterica.core.rdb.BaseEntity
import com.peterica.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "users")
internal class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "username")
    val username: String,

    @Column(name = "email")
    val email: String
) : BaseEntity() {
    companion object {
        fun fromDomain(domain: User) : UserEntity {
            return UserEntity(
                id = domain.id,
                username = domain.username,
                email = domain.email
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email
        )
    }
}
