package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeyofdev.spring_dto_mapper.domain.actor.Actor;
import com.jeyofdev.spring_dto_mapper.domain.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @NotEmpty(message = "The title is required.")
    @Size(min = 2, max = 100, message = "The title must be between 2 and 100 characters.")
    private String title;

    @Column(name = "country")
    @NotEmpty(message = "The country is required.")
    private String country;

    @Column(name = "year")
    @Min(value = 1900, message ="The year is required and must be greater than or equal to 1900.")
    @Max(value = 2024, message ="The year is required and must be less than or equal to 2024.")
    private int year;

    @Column(name = "rating")
    @Min(value = 0, message ="The year is required and must be greater than or equal to 0.")
    @Max(value = 10, message ="The year is required and must be less than or equal to 10.")
    private double rating;

    @Column(name = "synopsys")
    @NotEmpty(message = "The synopsys is required.")
    private String synopsys;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @JsonIgnoreProperties("movieList")
    private List<Actor> actorList = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties("movieList")
    private Category category;
}
