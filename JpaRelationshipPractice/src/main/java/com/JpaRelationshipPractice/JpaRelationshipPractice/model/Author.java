package com.JpaRelationshipPractice.JpaRelationshipPractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private Long id;

    private String name;

    private String bio;

    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    private Set<Books> books = new HashSet<>();


}
