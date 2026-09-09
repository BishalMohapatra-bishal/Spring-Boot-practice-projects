package com.Personal.Bookmark.Personal.Bookmark.repository;

import com.Personal.Bookmark.Personal.Bookmark.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
