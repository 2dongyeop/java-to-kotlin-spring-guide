package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
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

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장이 정상 동작한다.")
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

    @Test
    @DisplayName("유저 조회가 정상 동작한다.")
    fun getUsersTest() {
        // given
        userRepository.saveAll(
            listOf(
                User("A", 20),
                User("B", null),
            )
        )

        // when
        val users = userService.getUsers()

        // then
        assertThat(users).hasSize(2)
        assertThat(users).extracting("name").containsExactlyInAnyOrder("A", "B")
        assertThat(users).extracting("age").containsSequence(20, null)
    }

    @Test
    @DisplayName("유저 수정이 정상 동작한다.")
    fun updateUserTest() {
        // given
        val savedUser = userRepository.save(User("A", 20))
        val request = UserUpdateRequest(savedUser.id!!, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작한다.")
    fun deleteUserTest() {
        // given
        val savedUser = userRepository.save(User("A", 20))

        // when
        userService.deleteUser("A")

        // then
        val result = userRepository.findAll()
        assertThat(result).hasSize(0)
        assertThat(result).isEmpty()
    }
}