package com.example.controller;

import com.example.model.Note;
import com.example.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing notes.
 */
@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    /**
     * Create a new note.
     */
    @PostMapping
    public Note create(@Valid @RequestBody Note note) {
        return noteService.create(note);
    }

    /**
     * Get all notes.
     */
    @GetMapping
    public List<Note> getAll() {
        return noteService.getAll();
    }

    /**
     * Get a note by ID.
     */
    @GetMapping("/{id}")
    public Note getById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    /**
     * Update a note by ID.
     */
    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @Valid @RequestBody Note note) {
        return noteService.update(id, note);
    }

    /**
     * Delete a note by ID.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
