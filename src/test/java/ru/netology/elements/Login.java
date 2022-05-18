package ru.netology.elements;

import io.restassured.specification.RequestSpecification;
import ru.netology.entities.RegistrationDTO;

import static com.codeborne.selenide.Selenide.$x;
import static io.restassured.RestAssured.given;

public class Login {
    public static void login(RegistrationDTO user){
        $x("//input[@name = \"login\"]").setValue(user.getLogin());
        $x("//input[@name = \"password\"]").setValue(user.getPassword());
        $x("//button[@data-test-id = \"action-login\"]").click();
    }

    public static void postUser(RequestSpecification requestSpec, RegistrationDTO user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static void changeStatus(RequestSpecification requestSpec, RegistrationDTO user, String status) {
        user.setStatus(status);
        postUser(requestSpec, user);
    }

}
