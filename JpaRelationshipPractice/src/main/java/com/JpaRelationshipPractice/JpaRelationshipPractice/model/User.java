package com.JpaRelationshipPractice.JpaRelationshipPractice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

//    @OneToOne(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private UserProfile profile;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Records> bookRecords = new ArrayList<>();
}
