package org.example.travel_journal_app.service;

import org.example.travel_journal_app.entities.Journal;
import org.example.travel_journal_app.entities.Visibility;
import org.example.travel_journal_app.generated.model.*;
import org.example.travel_journal_app.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public JournalResponse createJournal(JournalCreateRequest request) {
        OffsetDateTime now = OffsetDateTime.now();

        Journal journal = new Journal();
        journal.setTitle(request.getTitle());
        journal.setDescription(request.getDescription());
        journal.setStartDate(request.getStartDate());
        journal.setEndDate(request.getEndDate());
        journal.setVisibility(mapVisibility(request.getVisibility()));
        journal.setCreatedAt(now);
        journal.setUpdatedAt(now);

        Journal saved = journalRepository.save(journal);
        return toResponse(saved);
    }

    public List<JournalResponse> getAllJournals() {
        return journalRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public JournalResponse getJournalById(Long journalId) {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));
        return toResponse(journal);
    }

    public JournalResponse replaceJournal(Long journalId, JournalPutRequest request) {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));

        journal.setTitle(request.getTitle());
        journal.setDescription(request.getDescription());
        journal.setStartDate(request.getStartDate());
        journal.setEndDate(request.getEndDate());
        journal.setVisibility(mapVisibility(request.getVisibility()));
        journal.setUpdatedAt(OffsetDateTime.now());

        Journal saved = journalRepository.save(journal);
        return toResponse(saved);
    }

    public JournalResponse patchJournal(Long journalId, JournalPatchRequest request) {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));

        if (request.getTitle() != null) journal.setTitle(request.getTitle());
        if (request.getDescription() != null) journal.setDescription(request.getDescription());
        if (request.getStartDate() != null) journal.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) journal.setEndDate(request.getEndDate());
        if (request.getVisibility() != null) journal.setVisibility(mapVisibility(request.getVisibility()));

        journal.setUpdatedAt(OffsetDateTime.now());

        Journal saved = journalRepository.save(journal);
        return toResponse(saved);
    }

    public void deleteJournal(Long journalId) {
        if (!journalRepository.existsById(journalId)) {
            throw new RuntimeException("Journal not found " + journalId);
        }
        journalRepository.deleteById(journalId);
    }

    private JournalResponse toResponse(Journal journal) {
        return new JournalResponse()
                .id(journal.getId())
                .title(journal.getTitle())
                .description(journal.getDescription())
                .startDate(journal.getStartDate())
                .endDate(journal.getEndDate() == null ? null : journal.getEndDate())
                .visibility(toGeneratedVisibility(journal.getVisibility()))
                .createdAt(journal.getCreatedAt())
                .updatedAt(journal.getUpdatedAt());
    }

    private Visibility mapVisibility(org.example.travel_journal_app.generated.model.Visibility visibility) {
        return Visibility.valueOf(visibility.getValue());
    }

    private org.example.travel_journal_app.generated.model.Visibility toGeneratedVisibility(Visibility visibility) {
        return org.example.travel_journal_app.generated.model.Visibility.fromValue(visibility.name());
    }
}
