package com.example.service;


import com.example.model.Note;
import com.example.repository.NoteRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNoteSuccess() {
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note createdNote = noteService.create(note);

        assertNotNull(createdNote);
        assertEquals("Test Title", createdNote.getTitle());
        assertEquals("Test Content", createdNote.getContent());
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testCreateNoteWithBlankTitle() {
        Note note = new Note();
        note.setTitle("");
        note.setContent("Test Content");

        assertThrows(ValidationException.class, () -> noteService.create(note));
        verify(noteRepository, never()).save(note);
    }

    @Test
    void testGetAllNotes() {
        when(noteRepository.findAll()).thenReturn(List.of(new Note(), new Note()));

        List<Note> notes = noteService.getAll();

        assertNotNull(notes);
        assertEquals(2, notes.size());
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    void testGetNoteByIdSuccess() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note retrievedNote = noteService.getById(1L);

        assertNotNull(retrievedNote);
        assertEquals(1L, retrievedNote.getId());
        assertEquals("Test Title", retrievedNote.getTitle());
        verify(noteRepository, times(1)).findById(1L);
    }

    @Test
    void testGetNoteByIdNotFound() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> noteService.getById(1L));
        verify(noteRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateNoteSuccess() {
        Note existingNote = new Note();
        existingNote.setId(1L);
        existingNote.setTitle("Old Title");
        existingNote.setContent("Old Content");

        Note updatedNote = new Note();
        updatedNote.setTitle("New Title");
        updatedNote.setContent("New Content");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(existingNote));
        when(noteRepository.save(any(Note.class))).thenReturn(updatedNote);

        Note result = noteService.update(1L, updatedNote);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getContent());
        verify(noteRepository, times(1)).findById(1L);
        verify(noteRepository, times(1)).save(existingNote);
    }

    @Test
    void testDeleteNoteSuccess() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(new Note()));

        noteService.delete(1L);

        verify(noteRepository, times(1)).deleteById(1L);
    }
}
