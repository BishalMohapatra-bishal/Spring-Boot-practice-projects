package com.JpaRelationshipPractice.JpaRelationshipPractice.repository;

import com.JpaRelationshipPractice.JpaRelationshipPractice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
