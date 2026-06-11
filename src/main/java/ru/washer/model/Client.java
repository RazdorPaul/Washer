package ru.washer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Client {
    @ToString.Exclude
    private Long id;

    private String nickname;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    @ToString.Exclude
    private String encryptPassword;

    public Client(String nickname,
                  String email,
                  String encryptPassword) {
        this.nickname = nickname;
        this.email = email;
        this.encryptPassword = encryptPassword;
    }
}
