package com.group.libraryapp.domain.user

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 15
 * @description
 */
interface UserRepositoryCustom {
    fun findAllWithHistories(): List<User>
}