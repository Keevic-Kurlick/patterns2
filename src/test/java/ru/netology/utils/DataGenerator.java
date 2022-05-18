package ru.netology.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.entities.RegistrationDTO;

import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class RegistrationDataGenerator {

        public static RegistrationDTO generateData(String status) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDTO(faker.name().firstName(), faker.internet().password(), status);
        }
    }
}
