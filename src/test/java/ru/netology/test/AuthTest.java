package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.elements.Login;
import ru.netology.entities.RegistrationDTO;
import ru.netology.utils.DataGenerator;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {
    private static RegistrationDTO user = DataGenerator
            .RegistrationDataGenerator
            .generateData(RegistrationDTO.STATUS_ACTIVE);

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpUserActiveStatus() {
        Login.postUser(requestSpec, user);
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void authPreregisteredUserWithActiveStatus() {
        Login.login(user);
        $x("//h2[contains(text(), \"Личный кабинет\")]").should(visible);
    }

    @Test
    void authPreregisteredUserWithBlockedStatus() {
        Login.changeStatus(requestSpec, user, RegistrationDTO.STATUS_BLOCKED);
        Login.login(user);
        $(".notification__title").shouldHave(text("Ошибка"));
        Login.changeStatus(requestSpec, user, RegistrationDTO.STATUS_ACTIVE);
    }

    @Test
    void authUnregisteredUser() {
        user = new RegistrationDTO("Karlson", "sdsdsd");
        Login.login(user);
        $(".notification__title").shouldHave(text("Ошибка"));
    }

    @Test
    void authWithEmptyValues() {
        user = new RegistrationDTO(" ", " ");
        Login.login(user);
        $x("//*[@data-test-id = \"login\"]//span[@class = \"input__sub\"]").
                shouldHave(text("Поле обязательно для заполнения"));
        $x("//*[@data-test-id = \"password\"]//span[@class = \"input__sub\"]").
                shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void authPreregisteredUserWithIncorrectPasswordActiveStatus(){

    }

    @Test
    void authPreregisteredUserWithIncorrectLoginActiveStatus(){

    }

    @Test
    void authPreregisteredUserWithIncorrectPasswordBlockedStatus(){

    }

    @Test
    void authPreregisteredUserWithIncorrectLoginBlockedStatus(){

    }

}

