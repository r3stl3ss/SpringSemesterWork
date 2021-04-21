package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;

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

    //TODO: приделать констрейнты к столбцам таблиц (касается всех)
    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String activationCode;

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
