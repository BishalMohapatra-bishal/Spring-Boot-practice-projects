package com.Personal.Bookmark.Personal.Bookmark.controller;

import com.Personal.Bookmark.Personal.Bookmark.dto.NoteRequestDto;
import com.Personal.Bookmark.Personal.Bookmark.dto.NoteResponseDto;
import com.Personal.Bookmark.Personal.Bookmark.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote( @Valid @RequestBody NoteRequestDto noteRequestDto) {
        NoteResponseDto responseDto = noteService.createNote(noteRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getNotes(
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(noteService.getNotes(tag, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequestDto requestDto ) {
        return ResponseEntity.ok(noteService.updateNote(id, requestDto));
    }
}
