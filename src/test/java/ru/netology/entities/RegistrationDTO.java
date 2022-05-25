package ru.netology.entities;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class RegistrationDTO {
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_BLOCKED = "blocked";

    String login;
    String password;
    String status;
}
