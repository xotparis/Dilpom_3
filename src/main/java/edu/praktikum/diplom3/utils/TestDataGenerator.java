package edu.praktikum.diplom3.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestDataGenerator {
    private static final Faker faker = new Faker();
    private static final Random RANDOM = new Random();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

}
