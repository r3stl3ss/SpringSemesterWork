package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;

@Entity
@Table(name = "users_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column
    private String phone;

    @Column
    private boolean phoneProved = false;

    private String activationCode;

    @OneToMany(mappedBy = "author")
    private List<Review> reviews;

    @Column(length = 150)
    private String about;

    private String pathToAvatar;

    @Column(nullable=true)
    private String currentPhoneConfirmCode;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, SUPERUSER
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isSuperuser() {
        return this.role == Role.SUPERUSER;
    }
}
