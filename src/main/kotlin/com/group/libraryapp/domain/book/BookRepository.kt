package com.group.libraryapp.domain.book

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 08
 * @description
 */
interface BookRepository : JpaRepository<Book, Long> {

    fun findByName(bookName: String): Book?
}