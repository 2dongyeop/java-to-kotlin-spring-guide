package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 08
 * @description
 */
interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String): User?
}