package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeyofdev.spring_dto_mapper.domain.actor.Actor;
import com.jeyofdev.spring_dto_mapper.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String title;

    @Column(name = "country")
    private String country;

    @Column(name = "year")
    private int year;

    @Column(name = "rating")
    private double rating;

    @Column(name = "synopsys")
    private String synopsys;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actorList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("movieList")
    private Category category;
}
