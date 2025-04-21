package ru.elizarov;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
class UniqueRandomGenerator {
    private final int min;
    private final int max;
    private List<Integer> values = new ArrayList<>(); // инициализация сразу
    private int index = 0;

    private void regenerate() {
        values = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            values.add(i);
        }
        Collections.shuffle(values);
        index = 0;
    }

    public int next() {
        if (index >= values.size()) {
            regenerate();
        }
        return values.get(index++);
    }
}
