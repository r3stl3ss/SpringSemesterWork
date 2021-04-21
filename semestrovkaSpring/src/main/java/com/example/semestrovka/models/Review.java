package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.File;

@Entity
@Table(name="reviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10000)
    private String text;

    @Column(length = 100)
    private String header;

    @Column(nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    private File picture;

    public enum Rating {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN
    }

}
