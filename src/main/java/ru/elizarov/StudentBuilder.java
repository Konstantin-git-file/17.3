package ru.elizarov;

import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

// 9.2.5 билдер студов
@RequiredArgsConstructor
class StudentBuilder {
    private final Predicate<Integer> gradeValidator;

    public Student build(String name, Integer... grades) {
        Student student = new Student(name, gradeValidator);
        for (int grade : grades) {
            student.addGrade(grade);
        }
        return student;
    }
}
