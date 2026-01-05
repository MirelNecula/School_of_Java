package org.example.travel_journal_app.service;

import org.example.travel_journal_app.entities.Journal;
import org.example.travel_journal_app.entities.JournalEntry;
import org.example.travel_journal_app.generated.model.JournalEntryCreateRequest;
import org.example.travel_journal_app.generated.model.JournalEntryResponse;
import org.example.travel_journal_app.repository.JournalEntryRepository;
import org.example.travel_journal_app.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalRepository journalRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository, JournalRepository journalRepository) {
        this.journalEntryRepository = journalEntryRepository;
        this.journalRepository = journalRepository;
    }

    public JournalEntryResponse createJournalEntry(Long journalId, JournalEntryCreateRequest request) {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));

        JournalEntry entry = new JournalEntry();
        entry.setJournal(journal);
        entry.setEntryDate(request.getEntryDate());
        entry.setTitle(request.getTitle());
        entry.setContent(request.getContent());
        entry.setMood(request.getMood());
        entry.setWeather(request.getWeather());

        JournalEntry saved = journalEntryRepository.save(entry);
        return toResponse(saved);
    }

    public List<JournalEntryResponse> getAllJournalEntries(Long journalId) {
        return journalEntryRepository.findAllByJournal_Id(journalId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public JournalEntryResponse getJournalEntryById(Long journalId, Long entryId) {
        JournalEntry entry = journalEntryRepository.findByIdAndJournal_Id(entryId, journalId)
                .orElseThrow(() -> new RuntimeException("Journal Entry not found " + entryId + " for Journal " + journalId));
        return toResponse(entry);
    }

    public void deleteJournalEntry(Long journalId, Long entryId) {
        JournalEntry entry = journalEntryRepository.findByIdAndJournal_Id(entryId, journalId)
                .orElseThrow(() -> new RuntimeException("Journal Entry not found " + entryId + " for Journal " + journalId));
        journalEntryRepository.delete(entry);
    }

    public JournalEntryResponse replaceJournalEntry(Long journalId, Long entryId,
                                                    org.example.travel_journal_app.generated.model.JournalEntryPutRequest request) {
        JournalEntry entry = journalEntryRepository.findByIdAndJournal_Id(entryId, journalId)
                .orElseThrow(() -> new RuntimeException("Journal Entry not found " + entryId + " for Journal " + journalId));

        entry.setEntryDate(request.getEntryDate());
        entry.setTitle(request.getTitle());
        entry.setContent(request.getContent());
        entry.setMood(request.getMood());
        entry.setWeather(request.getWeather());

        JournalEntry saved = journalEntryRepository.save(entry);
        return toResponse(saved);
    }

    public JournalEntryResponse patchJournalEntry(Long journalId, Long entryId,
                                                  org.example.travel_journal_app.generated.model.JournalEntryPatchRequest request) {
        JournalEntry entry = journalEntryRepository.findByIdAndJournal_Id(entryId, journalId)
                .orElseThrow(() -> new RuntimeException("Journal Entry not found " + entryId + " for Journal " + journalId));

        if (request.getEntryDate() != null) entry.setEntryDate(request.getEntryDate());
        if (request.getTitle() != null) entry.setTitle(request.getTitle());
        if (request.getContent() != null) entry.setContent(request.getContent());
        if (request.getMood() != null) entry.setMood(request.getMood());
        if (request.getWeather() != null) entry.setWeather(request.getWeather());

        JournalEntry saved = journalEntryRepository.save(entry);
        return toResponse(saved);
    }

    private JournalEntryResponse toResponse(JournalEntry entry) {
        return new JournalEntryResponse()
                .id(entry.getId())
                .journalId(entry.getJournal().getId())
                .entryDate(entry.getEntryDate())
                .title(entry.getTitle())
                .content(entry.getContent())
                .mood(entry.getMood())
                .weather(entry.getWeather())
                .createdAt(entry.getCreatedAt())
                .updatedAt(entry.getUpdatedAt());
    }
}