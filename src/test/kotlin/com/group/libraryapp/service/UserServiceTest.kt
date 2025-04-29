package com.group.libraryapp.service

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 04. 29
 * @description
 */
@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
) {

    @Test
    fun saveUserTest() {
        // given
        val userCreateRequest = UserCreateRequest("이동엽", null)

        // when
        userService.saveUser(userCreateRequest)

        // then
        val allUsers = userRepository.findAll()
        assertThat(allUsers).hasSize(1)
        assertThat(allUsers[0].name).isEqualTo(userCreateRequest.name)
        assertThat(allUsers[0].age).isNull()
    }
}