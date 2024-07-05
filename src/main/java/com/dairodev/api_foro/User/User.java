package com.dairodev.api_foro.User;

import com.dairodev.api_foro.Answer.Answer;
import com.dairodev.api_foro.Profile.Profile;
import com.dairodev.api_foro.Topic.model.Topic;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
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

    @OneToMany(mappedBy = "author")
    private List<Answer> answers;

    @OneToMany(mappedBy = "author")
    private List<Topic> topics;

    @ManyToMany
    @JoinTable(
            name = "users_profiles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id")
    )
    private Set<Profile> profiles;


    public User(UUID id, String name, String email, String password) {
        super();
        setId(id);
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public static User register(String name, String email, String password) {
        return new User(UUID.randomUUID(), name, email, password);
    }
}
