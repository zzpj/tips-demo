package com.example.jdk.update.jdk17;

import java.util.Comparator;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class ShapeRunner {
    public static void main(String[] args) {

        ShapeRunner shapeRunner = new ShapeRunner();
        System.out.println(shapeRunner.checkObject(new Human("Alan", 30, "Freakfighter")));
        System.out.println(shapeRunner.checkObject(new Circle()));
        System.out.println(shapeRunner.checkObject(new Triangle()));
        System.out.println(shapeRunner.checkObject(null));
        System.out.println(shapeRunner.checkObject(new AnyOtherObject()));
    }


    // JDK 21
    private String checkObject(Object obj) {
        return switch (obj) {
            case Human h -> "Name: %s, age: %s and profession: %s".formatted(h.name(), h.age(), h.profession());
            case Circle c when c.getNumberOfSides() == 0 -> "This is a circle";
            case Circle c when c.getNumberOfSides() == 4 -> "This is weird circle that is remaining square...";
            case Triangle t -> "It is a triangle";
            case null -> "It is null";
            default -> "It is an object";
        };
    }

    static class AnyOtherObject {}


}
