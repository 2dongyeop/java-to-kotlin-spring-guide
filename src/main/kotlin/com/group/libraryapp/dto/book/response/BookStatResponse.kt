package com.group.libraryapp.dto.book.response

import com.group.libraryapp.domain.book.BookType

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 14
 * @description
 */
data class BookStatResponse(
    val type: BookType,
    val count: Long
)