package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Column(length = 100000)
    private String text;

    @Column(length = 100)
    private String header;

    @Column
    private String pathToPicture;

    @Column
    public double rating;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;
}
