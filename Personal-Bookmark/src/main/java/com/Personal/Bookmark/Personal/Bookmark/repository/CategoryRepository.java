package com.Personal.Bookmark.Personal.Bookmark.repository;

import com.Personal.Bookmark.Personal.Bookmark.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
