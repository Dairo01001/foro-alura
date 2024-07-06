package com.dairodev.api_foro.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class User {
    private @Id UUID id;
    private String name;
    private String email;
    private String password;


    public static User register(String name, String email, String password) {
        return new User(UUID.randomUUID(), name, email, password);
    }
}
