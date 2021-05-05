package com.example.semestrovka.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition="text", name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}