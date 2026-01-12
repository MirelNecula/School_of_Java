package org.example.travel_journal_app.repository;

import org.example.travel_journal_app.entities.JournalCollaborator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalCollaboratorRepository extends JpaRepository<JournalCollaborator, Long> {
    List<JournalCollaborator> findByJournalId(Long journalId);

    Optional<JournalCollaborator> findByJournalIdAndUserId(Long journalId, Long userId);

    boolean existsByJournalIdAndUserId(Long journalId, Long userId);

    List<JournalCollaborator> findByUserId(Long userId);
}
