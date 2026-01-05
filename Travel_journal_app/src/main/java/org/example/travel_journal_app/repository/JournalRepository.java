package org.example.travel_journal_app.repository;

import org.example.travel_journal_app.entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JournalRepository  extends JpaRepository<Journal, Long> {
}
