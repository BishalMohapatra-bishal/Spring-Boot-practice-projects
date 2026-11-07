package com.JpaRelationshipPractice.JpaRelationshipPractice.repository;

import com.JpaRelationshipPractice.JpaRelationshipPractice.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
}
