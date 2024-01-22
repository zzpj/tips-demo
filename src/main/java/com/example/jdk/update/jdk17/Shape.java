package com.example.jdk.update.jdk17;

sealed interface Shapeable permits Shape {
}

public abstract sealed class Shape implements Shapeable permits Circle, Triangle {
    abstract int getNumberOfSides();
}

final class Circle extends Shape {
    @Override
    final int getNumberOfSides() {
        return 0;
    }
}

final class Triangle extends Shape {

    @Override
    int getNumberOfSides() {
        return 3;
    }
}

record Human(String name, int age, String profession) {
}