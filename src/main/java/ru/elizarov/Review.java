package ru.elizarov;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// 9.2.2 Отзывы
@Getter
@ToString
@RequiredArgsConstructor
class Review {
    private final String text;
    private final int rating;
}
