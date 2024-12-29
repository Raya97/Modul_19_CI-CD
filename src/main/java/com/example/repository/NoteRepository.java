package com.example.repository;


import com.example.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Note entities.
 * Provides CRUD operations for the Note entity.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
