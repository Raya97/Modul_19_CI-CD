package com.example.service;

import com.example.model.Note;
import com.example.repository.NoteRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing notes.
 */
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Create a new note.
     * @param note The note to create.
     * @return The created note.
     * @throws ValidationException If title or content is blank.
     */
    public Note create(Note note) {
        if (note.getTitle().isBlank() || note.getContent().isBlank()) {
            throw new ValidationException("Title and content must not be blank");
        }
        return noteRepository.save(note);
    }

    /**
     * Get all notes.
     * @return A list of all notes.
     */
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    /**
     * Get a note by ID.
     * @param id The ID of the note to retrieve.
     * @return The note with the specified ID.
     * @throws ValidationException If the note is not found.
     */
    public Note getById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new ValidationException("Note not found"));
    }

    /**
     * Update an existing note.
     * @param id The ID of the note to update.
     * @param updatedNote The updated note data.
     * @return The updated note.
     */
    public Note update(Long id, Note updatedNote) {
        Note note = getById(id);
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        return noteRepository.save(note);
    }

    /**
     * Delete a note by ID.
     * @param id The ID of the note to delete.
     */
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
