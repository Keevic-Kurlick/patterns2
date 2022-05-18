package ru.netology.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.entities.RegistrationDTO;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class RegistrationDataGenerator {

        public static RegistrationDTO generateData(String status) {
            Faker faker = new Faker();
            return new RegistrationDTO(faker.name().firstName(), faker.internet().password(), status);
        }

        public static String generateLogin() {
            Faker faker = new Faker();
            return faker.name().firstName();
        }

        public static String generatePassword() {
            Faker faker = new Faker();
            return faker.internet().password();
        }
    }
}
