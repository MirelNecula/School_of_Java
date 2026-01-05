package org.example.travel_journal_app.api.controller;

import org.example.travel_journal_app.generated.api.JournalsApi;
import org.example.travel_journal_app.generated.model.JournalCreateRequest;
import org.example.travel_journal_app.generated.model.JournalPatchRequest;
import org.example.travel_journal_app.generated.model.JournalPutRequest;
import org.example.travel_journal_app.generated.model.JournalResponse;
import org.example.travel_journal_app.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalController implements JournalsApi {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @Override
    public ResponseEntity<JournalResponse> createJournal(JournalCreateRequest journalCreateRequest) {
        JournalResponse created = journalService.createJournal(journalCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<List<JournalResponse>> getAllJournals() {
        return ResponseEntity.ok(journalService.getAllJournals());
    }

    @Override
    public ResponseEntity<JournalResponse> getJournalById(Long journalId) {
        return ResponseEntity.ok(journalService.getJournalById(journalId));
    }

    @Override
    public ResponseEntity<JournalResponse> replaceJournal(Long journalId, JournalPutRequest journalPutRequest) {
        return ResponseEntity.ok(journalService.replaceJournal(journalId, journalPutRequest));
    }

    @Override
    public ResponseEntity<JournalResponse> patchJournal(Long journalId, JournalPatchRequest journalPatchRequest) {
        return ResponseEntity.ok(journalService.patchJournal(journalId, journalPatchRequest));
    }

    @Override
    public ResponseEntity<Void> deleteJournal(Long journalId) {
        journalService.deleteJournal(journalId);
        return ResponseEntity.noContent().build();
    }
}
