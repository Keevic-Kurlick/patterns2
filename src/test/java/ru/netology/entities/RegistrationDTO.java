package ru.netology.entities;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

@AllArgsConstructor
@Value
public class RegistrationDTO {
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_BLOCKED = "blocked";

    String login;
    String password;
    @NonFinal
    String status;

    public RegistrationDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
