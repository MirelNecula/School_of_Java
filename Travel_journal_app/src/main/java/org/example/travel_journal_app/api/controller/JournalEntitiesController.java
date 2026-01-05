package org.example.travel_journal_app.api.controller;

import org.example.travel_journal_app.generated.api.JournalEntriesApi;
import org.example.travel_journal_app.generated.model.JournalEntryCreateRequest;
import org.example.travel_journal_app.generated.model.JournalEntryPatchRequest;
import org.example.travel_journal_app.generated.model.JournalEntryPutRequest;
import org.example.travel_journal_app.generated.model.JournalEntryResponse;
import org.example.travel_journal_app.service.JournalEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalEntitiesController implements JournalEntriesApi {

    private final JournalEntryService journalEntryService;

    public JournalEntitiesController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @Override
    public ResponseEntity<JournalEntryResponse> createJournalEntry(
            Long journalId,
            JournalEntryCreateRequest request
    ) {
        JournalEntryResponse created =
                journalEntryService.createJournalEntry(journalId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<List<JournalEntryResponse>> getAllJournalEntries(Long journalId) {
        return ResponseEntity.ok(journalEntryService.getAllJournalEntries(journalId));
    }

    @Override
    public ResponseEntity<JournalEntryResponse> getJournalEntryById(Long journalId, Long entryId) {
        return ResponseEntity.ok(journalEntryService.getJournalEntryById(journalId, entryId));
    }

    @Override
    public ResponseEntity<JournalEntryResponse> replaceJournalEntry(
            Long journalId,
            Long entryId,
            JournalEntryPutRequest request
    ) {
        JournalEntryResponse updated = journalEntryService.replaceJournalEntry(journalId, entryId, request);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<JournalEntryResponse> patchJournalEntry(
            Long journalId,
            Long entryId,
            JournalEntryPatchRequest request
    ) {
        JournalEntryResponse updated = journalEntryService.patchJournalEntry(journalId, entryId, request);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteJournalEntry(Long journalId, Long entryId) {
        journalEntryService.deleteJournalEntry(journalId, entryId);
        return ResponseEntity.noContent().build();
    }
}
