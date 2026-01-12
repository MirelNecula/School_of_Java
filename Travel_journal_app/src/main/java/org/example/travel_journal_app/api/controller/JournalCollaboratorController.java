package org.example.travel_journal_app.api.controller;


import org.example.travel_journal_app.generated.api.CollaborationApi;
import org.example.travel_journal_app.generated.model.CollaboratorAddRequest;
import org.example.travel_journal_app.generated.model.CollaboratorPutRequest;
import org.example.travel_journal_app.generated.model.CollaboratorResponse;
import org.example.travel_journal_app.service.JournalCollaboratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalCollaboratorController implements CollaborationApi {
    private final JournalCollaboratorService journalCollaboratorService;

    public JournalCollaboratorController(JournalCollaboratorService journalCollaboratorService) {
        this.journalCollaboratorService = journalCollaboratorService;
    }

    @Override
    public ResponseEntity<CollaboratorResponse> addCollaboratorToJournal(Long journalId, CollaboratorAddRequest collaboratorAddRequest) {
        var created = journalCollaboratorService.addCollaboratorToJournal(journalId, collaboratorAddRequest);
        return ResponseEntity.status(201).body(created);
    }

    @Override
    public ResponseEntity<List<CollaboratorResponse>> getJournalCollaborators(Long journalId) {
        var list = journalCollaboratorService.getJournalCollaborators(journalId);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<CollaboratorResponse> replaceJournalCollaborator(Long journalId, Long collaboratorUserId, CollaboratorPutRequest collaboratorPutRequest) {
        var updated = journalCollaboratorService.replaceJournalCollaborator(journalId, collaboratorUserId, collaboratorPutRequest);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteJournalCollaborator(Long journalId, Long collaboratorUserId) {
        journalCollaboratorService.deleteJournalCollaborator(journalId, collaboratorUserId);
        return ResponseEntity.noContent().build();
    }
}
