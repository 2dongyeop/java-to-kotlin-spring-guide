package com.group.libraryapp.domain.user.loanhistory

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 14
 * @description
 */
enum class UserLoanStatus {
    RETURNED, // 반납되어 있는 상태
    LOANED,   // 대출 중인 상태
}