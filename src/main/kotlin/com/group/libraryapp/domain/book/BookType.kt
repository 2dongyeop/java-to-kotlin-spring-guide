package com.group.libraryapp.domain.book

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 05. 14
 * @description
 */
enum class BookType {
    COMPUTER,
    ECONOMY,
    /**
     * 사회 분야는 2022-05-14 이후로 입고되지 않지만, DB에는 과거 기록이 존재
     */
    SOCIETY,
    LANGUAGE,
    SCIENCE,
}