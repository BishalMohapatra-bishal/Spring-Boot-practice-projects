package com.JpaRelationshipPractice.JpaRelationshipPractice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    private String publisher;

    private LocalDate publishedDate;

    private String genre;

    private String language;

    @Column(nullable = false)
    private Integer totalCopies;

    @Column(nullable = false)
    private Integer availableCopies;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Many-to-Many with Author
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

//    public void setAuthor(Author author) {
//        this.authors = author;
//    }

    // One-to-Many with Records (Borrowing transactions)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Records> records = new HashSet<>();
}