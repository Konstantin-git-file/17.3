package ru.elizarov;

import java.util.List;

class ThreeColorTrafficLight implements TrafficLight {
    private final List<String> colors = List.of("зеленый", "желтый", "красный", "желтый");
    private int currentIndex = 0;

    @Override
    public void next() {
        currentIndex = (currentIndex + 1) % colors.size();
        System.out.println(getCurrentColor());
    }

    @Override
    public String getCurrentColor() {
        return colors.get(currentIndex);
    }
}
