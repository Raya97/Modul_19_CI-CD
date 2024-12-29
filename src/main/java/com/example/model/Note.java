package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity representing a note.
 */
@Entity
@Data
public class Note {

    /**
     * Unique identifier for the note.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the note. Max length: 100 characters.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Content of the note. Max length: 1000 characters.
     */
    @Column(nullable = false, length = 1000)
    private String content;

    /**
     * Timestamp of when the note was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
