package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 08
 * @description
 */
@Entity
class UserLoanHistory(

    @ManyToOne
    val user: User,

    val bookName: String,

    var isReturn: Boolean,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    fun doReturn() {
        this.isReturn = true
    }
}