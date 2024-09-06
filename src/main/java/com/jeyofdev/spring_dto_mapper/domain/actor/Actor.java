package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeyofdev.spring_dto_mapper.domain.movie.Movie;
import com.jeyofdev.spring_dto_mapper.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters.")
    private String name;

    @Column(name = "country")
    @NotEmpty(message = "The country is required.")
    private String country;

    @Column(name = "age")
    @Min(value = 18, message ="The age is required and must be over 18.")
    private int age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "The gender is required.")
    private Gender gender;

    @Column(name = "biography")
    @NotEmpty(message = "The biography is required.")
    private String biography;

    @ManyToMany(mappedBy = "actorList", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("actorList")
    private List<Movie> movieList = new ArrayList<>();
}
