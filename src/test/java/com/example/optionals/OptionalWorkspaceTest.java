package com.example.optionals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OptionalWorkspaceTest {

    OptionalWorkspace sut = new OptionalWorkspace();

    @Test
    void shouldGetCarInsuranceNameForGivenDriver() {

        Person person = new Person();
        String carInsuranceName = sut.getCarInsuranceName(person);
        assertNotNull(carInsuranceName);
    }

    @Test
    void shouldGetCarInsuranceNameWithoutNPE() {
        Person person = new Person();
        String carInsuranceName = sut.getCarInsuranceNameNPE(person);
        assertNotNull(carInsuranceName);
    }

    @Test
    void shouldGetCarInsuranceNameOptional() {
        String carInsuranceNameWithOptional = sut.getCarInsuranceNameWithOptional();
        assertEquals("No car insurance name", carInsuranceNameWithOptional);
    }

    @Test
    void shouldGetCarInsuranceNameFromPerson() {
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();
        insurance.setName("Link4");
        car.setInsurance(insurance);
        person.setCar(car);
        assertEquals("Link4", sut.getCarInsuranceNameWithOptional(person));
    }

}