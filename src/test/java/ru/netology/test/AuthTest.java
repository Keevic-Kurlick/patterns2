package ru.netology.test;

import org.junit.jupiter.api.AfterEach;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.api.UserApi;
import ru.netology.elements.Login;
import ru.netology.entities.RegistrationDTO;
import ru.netology.utils.DataGenerator;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {
    private static RegistrationDTO activeUser;
    private static RegistrationDTO blockedUser;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        activeUser = DataGenerator
                .RegistrationDataGenerator
                .generateData(RegistrationDTO.STATUS_ACTIVE);

        blockedUser = DataGenerator.
                RegistrationDataGenerator.
                generateData(RegistrationDTO.STATUS_BLOCKED);
    }

    @AfterEach
    void tearDown() {
        activeUser = null;
        blockedUser = null;
    }

    @Test
    void authPreregisteredUserWithActiveStatus() {
        UserApi.postUser(activeUser);
        Login.login(activeUser);
        $x("//h2[contains(text(), \"Личный кабинет\")]").should(visible);
    }

    @Test
    void authPreregisteredUserWithBlockedStatus() {
        UserApi.postUser(blockedUser);
        Login.login(blockedUser);
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void authUnregisteredUser() {
        activeUser = DataGenerator.RegistrationDataGenerator.generateData(RegistrationDTO.STATUS_ACTIVE);
        Login.login(activeUser);
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void authWithEmptyValues() {
        activeUser = new RegistrationDTO(" ", " ");
        Login.login(activeUser);
        $x("//*[@data-test-id = \"login\"]//span[@class = \"input__sub\"]").
                shouldHave(text("Поле обязательно для заполнения"));
        $x("//*[@data-test-id = \"password\"]//span[@class = \"input__sub\"]").
                shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void authPreregisteredUserWithIncorrectPasswordActiveStatus() {
        UserApi.postUser(activeUser);
        activeUser = new RegistrationDTO(activeUser.getLogin(), DataGenerator.RegistrationDataGenerator.generatePassword(), RegistrationDTO.STATUS_ACTIVE);
        Login.login(activeUser);
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void authPreregisteredUserWithIncorrectLoginActiveStatus() {
        UserApi.postUser(activeUser);
        activeUser = new RegistrationDTO(DataGenerator.RegistrationDataGenerator.generateLogin(), activeUser.getPassword(), RegistrationDTO.STATUS_ACTIVE);
        Login.login(activeUser);
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void authPreregisteredUserWithIncorrectPasswordBlockedStatus() {
        UserApi.postUser(blockedUser);
        blockedUser = new RegistrationDTO(blockedUser.getLogin(), DataGenerator.RegistrationDataGenerator.generatePassword(), RegistrationDTO.STATUS_BLOCKED);
        Login.login(blockedUser);
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void authPreregisteredUserWithIncorrectLoginBlockedStatus() {
        UserApi.postUser(blockedUser);
        blockedUser = new RegistrationDTO(DataGenerator.RegistrationDataGenerator.generateLogin(), blockedUser.getPassword(), RegistrationDTO.STATUS_BLOCKED);
        Login.login(blockedUser);
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

}

