package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 08
 * @description
 */
interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String): User?

    @Query("""
        SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories
    """)
    fun findAllWithHistories(): List<User>
}