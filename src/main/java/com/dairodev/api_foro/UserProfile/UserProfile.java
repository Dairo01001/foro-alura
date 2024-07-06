package com.dairodev.api_foro.UserProfile;

import com.dairodev.api_foro.Profile.model.Profile;
import com.dairodev.api_foro.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "UserProfile")
@Table(name = "user_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class UserProfile {
    private @Id UUID id;
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public static UserProfile register(User user, Profile profile) {
        return new UserProfile(UUID.randomUUID(), LocalDate.now(), user, profile);
    }
}
