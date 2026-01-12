package org.example.travel_journal_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.travel_journal_app.entities.JournalEntry;

import java.util.List;
import java.util.Optional;

public interface JournalEntryRepository extends JpaRepository<JournalEntry,Long> {

    List<JournalEntry> findAllByJournal_Id(Long journalId);
    Optional<JournalEntry> findByIdAndJournal_Id(Long id, Long journalId);
}
