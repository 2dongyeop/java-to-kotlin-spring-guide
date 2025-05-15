package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
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

    @Autowired
    private lateinit var userLoanHistoryRepository: UserLoanHistoryRepository

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
        userRepository.save(User("A", 20))

        // when
        userService.deleteUser("A")

        // then
        val result = userRepository.findAll()
        assertThat(result).hasSize(0)
        assertThat(result).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다.")
    fun getUserHistoriesTest1() {
        // given
        userRepository.save(User("A", 20))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동작한다.")
    fun getUserHistoriesTest2() {
        // given
        val savedUser = userRepository.save(User("A", 20))
        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(savedUser, "책1", UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(savedUser, "책2", UserLoanStatus.LOANED)
            )
        )

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).hasSize(2)
        assertThat(results[0].books[0].isReturn).isTrue()
        assertThat(results[0].books[1].isReturn).isFalse()
        assertThat(results[0].books).extracting("name")
            .containsExactlyInAnyOrder("책1", "책2")
    }
}