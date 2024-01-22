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


/*
public String checkObject(Object obj) {
    return switch (obj) {
        case Human h -> "Name: %s, age: %s and profession: %s".formatted(h.name(), h.age(), h.profession());
        case Circle c -> "This is a circle";
        case Shape s -> "It is just a shape";
        case null -> "It is null";
        default -> "It is an object";
    };
}

public String checkShape(Shape shape) {
    return switch (shape) {
        case Triangle t && (t.getNumberOfSides() != 3) -> "This is a weird triangle";
        case Circle c && (c.getNumberOfSides() != 0) -> "This is a weird circle";
            default -> "Just a normal shape";
    };
}
//*/