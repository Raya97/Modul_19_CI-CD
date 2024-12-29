package com.example.controller;

import com.example.model.Note;
import com.example.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void testGetAllNotes() throws Exception {
        when(noteService.getAll()).thenReturn(List.of(new Note(), new Note()));

        mockMvc.perform(get("/api/notes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService, times(1)).getAll();
    }

    @Test
    void testCreateNote() throws Exception {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setContent("Test Content");

        when(noteService.create(any(Note.class))).thenReturn(note);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\",\"content\":\"Test Content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));

        verify(noteService, times(1)).create(any(Note.class));
    }

    @Test
    void testGetNoteById() throws Exception {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");

        when(noteService.getById(1L)).thenReturn(note);

        mockMvc.perform(get("/api/notes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"));

        verify(noteService, times(1)).getById(1L);
    }
}
