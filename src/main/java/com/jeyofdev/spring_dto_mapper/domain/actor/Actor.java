package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeyofdev.spring_dto_mapper.domain.movie.Movie;
import jakarta.persistence.*;
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
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "biography")
    private String biography;

    @ManyToMany(mappedBy = "actorList", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("actorList")
    private List<Movie> movieList = new ArrayList<>();
}
