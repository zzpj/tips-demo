package com.example.reflection;

public class Dog extends Animal implements Motion {

    private boolean domesticated;

    public Dog(String name, boolean domesticated) {
        super(name);
        this.domesticated = domesticated;
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    protected String getSound() {
        return "barks";
    }

    public boolean isDomesticated() {
        return domesticated;
    }
}
