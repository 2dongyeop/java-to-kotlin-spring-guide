package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 15
 * @description
 */
@Component
class BookQuerydslRepository(
    private val queryFactory: JPAQueryFactory
) {

    /*
     * Projections.constructor() : 특정 컬럼만 가져오겠다 라는 의미
     * 아래에서는 Entity가 아닌 DTO로 리턴받기 때문에 Projections.constructor()로 생성자 작성
     */
    fun getStat(): List<BookStatResponse> {
        return queryFactory.select(
            Projections.constructor(
                BookStatResponse::class.java,
                book.type,
                book.id.count()
            )
        )
            .from(book)
            .groupBy(book.type)
            .fetch()

    }
}