package com.example.jdk.update.jdk17;

import java.util.Comparator;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class PseudoRandomNumberGeneratorShowroom {

    public static void main(String[] args) {

        RandomGeneratorFactory.all()
                .sorted(Comparator.comparing(RandomGeneratorFactory::name))
                .forEach(factory -> System.out.println(String.format("%s\t%s\t%s\t%s",
                        factory.group(),
                        factory.name(),
                        factory.isJumpable(),
                        factory.isSplittable())));

        RandomGenerator gen = RandomGenerator.of("L128X256MixRandom");
        System.out.printf(" Random number = %d",gen.nextInt(100));
    }
}
