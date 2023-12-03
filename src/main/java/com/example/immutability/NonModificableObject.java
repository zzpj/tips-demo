package com.example.immutability;

public /* final */ class NonModificableObject {

    private final Long id;
    private final String name;
    private final Double value;

    public NonModificableObject(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}


/**
 * 5 simple rules:
 * - Don’t provide any methods that modify the object’s state
 * - Ensure that the class can’t be extended
 * - Make all fields final
 * - Make all fields private.
 * - Ensure exclusive access to any mutable components.
 */