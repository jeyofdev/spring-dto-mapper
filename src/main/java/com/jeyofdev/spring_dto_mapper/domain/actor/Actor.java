package com.jeyofdev.spring_dto_mapper.domain.actor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private int country;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private double gender;

    @Column(name = "description")
    private String description;
}
