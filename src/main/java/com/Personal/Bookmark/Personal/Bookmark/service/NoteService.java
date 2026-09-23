package com.Personal.Bookmark.Personal.Bookmark.service;


import com.Personal.Bookmark.Personal.Bookmark.dto.NoteRequestDto;
import com.Personal.Bookmark.Personal.Bookmark.dto.NoteResponseDto;
import com.Personal.Bookmark.Personal.Bookmark.entity.Category;
import com.Personal.Bookmark.Personal.Bookmark.entity.Note;
import com.Personal.Bookmark.Personal.Bookmark.entity.Tag;
import com.Personal.Bookmark.Personal.Bookmark.exception.ResourceNotFound;
import com.Personal.Bookmark.Personal.Bookmark.repository.CategoryRepository;
import com.Personal.Bookmark.Personal.Bookmark.repository.NoteRepository;
import com.Personal.Bookmark.Personal.Bookmark.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Transactional
    public NoteResponseDto createNote(NoteRequestDto request) {
        Note note = new Note();
        note.setTitle(request.title());
        note.setContent(request.content());
        note.setUrl(request.url());

        if (request.categoryName() != null && !request.categoryName().isBlank()) {
            Category category = processCategory(request.categoryName());
            note.setCategory(category);
        }

        note.setTags(processTags(request.tagNames()));

        Note savedNote = noteRepository.save(note);
        return mapToDTO(savedNote);
    }

    public List<NoteResponseDto> getNotes(String tag, String category) {
        List<Note> notes = noteRepository.findByFilter(
                tag != null ? tag.toLowerCase().trim() : null,
                category != null ? category.toLowerCase().trim() : null
        );
        return notes.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public NoteResponseDto getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Note not found with id: " + id));
        return mapToDTO(note);
    }

    @Transactional
    public NoteResponseDto updateNote(Long id, NoteRequestDto request) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Note not found with id: " + id));

        note.setTitle(request.title());
        note.setContent(request.content());
        note.setUrl(request.url());

        if (request.categoryName() != null && !request.categoryName().isBlank()) {
            note.setCategory(processCategory(request.categoryName()));
        } else {
            note.setCategory(null);
        }

        note.setTags(processTags(request.tagNames()));

        Note updatedNote = noteRepository.save(note);
        return mapToDTO(updatedNote);
    }

    @Transactional
    public NoteResponseDto togglePin(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Note not found with id: " + id));
        note.setPinned(!note.isPinned());
        return mapToDTO(noteRepository.save(note));
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFound("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }

    private Category processCategory(String categoryName) {
        String name = categoryName.toLowerCase().trim();
        return categoryRepository.findByName(name)
                .orElseGet(() -> categoryRepository.save(new Category(name)));
    }

    private Set<Tag> processTags(Set<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return new HashSet<>();
        }
        Set<Tag> tags = new HashSet<>();
        for (String rawName : tagNames) {
            String name = rawName.toLowerCase().trim();
            if (name.isEmpty()) continue;
            Tag tag = tagRepository.findByName(name)
                    .orElseGet(() -> tagRepository.save(new Tag(name)));
            tags.add(tag);
        }
        return tags;
    }

    private NoteResponseDto mapToDTO(Note note) {
       return new NoteResponseDto(
               note.getId(),
               note.getTitle(),
               note.getContent(),
               note.getUrl(),
               note.isPinned(),
               note.getCreatedAt(),
               note.getCategory() != null ? note.getCategory().getCategoryName() : null,
               note.getTags().stream().map(Tag::getTagName).collect(Collectors.toSet())
       );
    }
}
