package com.Personal.Bookmark.Personal.Bookmark.repository;

import com.Personal.Bookmark.Personal.Bookmark.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT DISTINCT n FROM Note n LEFT JOIN n.tags t WHERE " +
    "(:tagName IS NULL OR t.name = :tagName) AND " +
    "(:CATEGORY is null or N.CATEGORY,NAME = :category)")
    List<Note> findByFilter(@Param("tagName") String tagName, @Param("category") String category);
}
