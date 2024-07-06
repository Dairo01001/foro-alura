package com.dairodev.api_foro.Profile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity(name = "Profile")
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Profile {
    private @Id UUID id;
    private String name;

    public static Profile register(String name) {
        return new Profile(UUID.randomUUID(), name);
    }
}
