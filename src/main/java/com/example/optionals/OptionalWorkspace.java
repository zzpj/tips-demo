package com.example.optionals;

import lombok.Data;

import java.util.Optional;

public class OptionalWorkspace {

    String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    String getCarInsuranceNameNPE(Person person) {

        if(person != null) {
            Car car = person.getCar();
            if(car != null) {
                Insurance insurance = car.getInsurance();
                if(insurance != null) {
                    return insurance.getName();
                }
            }
        }

        return "Null somewhere...";
    }

    String getCarInsuranceNameWithOptional() {
        Optional<Person> optPerson = Optional.empty();
        Optional<Car> optCar = optPerson.map(Person::getCar);
        Optional<Insurance> optInsurance = optCar.map(Car::getInsurance);
        return optInsurance.map(Insurance::getName).orElse("No car insurance name");
    }

    String getCarInsuranceNameWithOptional(Person person) {
        Optional<Person> optPerson = Optional.of(person);
        return optPerson
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("No car insurance name")
                ;
    }
}


@Data
class Insurance {
    private String name;
}

@Data
class Car {
    private Insurance insurance;
}

@Data
class Person {
    private Car car;
}
