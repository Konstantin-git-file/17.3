package ru.elizarov;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
@ToString
class Student {
    private final String name;
    private final List<Integer> grades = new ArrayList<>();
    private final Predicate<Integer> gradeValidator;

    public void addGrade(int grade) {
        if (gradeValidator.test(grade)) {
            grades.add(grade);
        } else {
            throw new IllegalArgumentException("Нет такой оценки: " + grade);
        }
    }
}
