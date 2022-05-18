package ru.netology.entities;

public class RegistrationDTO {
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_BLOCKED = "blocked";

    private final String login;
    private final String password;
    private String status;


    public RegistrationDTO(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public RegistrationDTO(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
