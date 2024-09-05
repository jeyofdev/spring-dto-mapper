package com.jeyofdev.spring_dto_mapper.domain.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "country")
    private String country;

    @Column(name = "year")
    private int year;

    @Column(name = "rating")
    private double rating;

    @Column(name = "synopsys")
    private String synopsys;
}
