package com.example.reflection;

public class Sparrow extends Animal implements Flyable, Motion {
    public Sparrow(String name) {
        super(name);
    }

    public Sparrow() {
        super("NPC Sparrow");
    }

    @Override
    protected String getSound() {
        return "tweets";
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public String getLocomotion() {
        return "fly and walks";
    }
}
