package com.jeyofdev.spring_dto_mapper.domain.category;

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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnoreProperties("category")
    private List<Movie> movieList = new ArrayList<>();
}
