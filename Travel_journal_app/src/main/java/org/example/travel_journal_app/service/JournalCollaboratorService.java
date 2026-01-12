package org.example.travel_journal_app.service;

import org.example.travel_journal_app.entities.CollaboratorRole;
import org.example.travel_journal_app.entities.Journal;
import org.example.travel_journal_app.entities.JournalCollaborator;
import org.example.travel_journal_app.entities.Visibility;
import org.example.travel_journal_app.generated.model.CollaboratorAddRequest;
import org.example.travel_journal_app.generated.model.CollaboratorPutRequest;
import org.example.travel_journal_app.generated.model.CollaboratorResponse;
import org.example.travel_journal_app.repository.JournalCollaboratorRepository;
import org.example.travel_journal_app.repository.JournalRepository;
import org.example.travel_journal_app.repository.UserRepository;
import org.example.travel_journal_app.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class JournalCollaboratorService {

    private final JournalRepository journalRepository;
    private final UserRepository userRepository;
    private final JournalCollaboratorRepository journalCollaboratorRepository;
    private final CurrentUserProvider currentUserProvider;

    public JournalCollaboratorService(JournalRepository journalRepository,
                                      UserRepository userRepository,
                                      JournalCollaboratorRepository journalCollaboratorRepository,
                                      CurrentUserProvider currentUserProvider) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
        this.journalCollaboratorRepository = journalCollaboratorRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public CollaboratorResponse addCollaboratorToJournal(Long journalId, CollaboratorAddRequest request) {

        var currentUserId = currentUserProvider.getCurrentUserId();


        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));


        var currentUserCollaboration = journalCollaboratorRepository
                .findByJournalIdAndUserId(journalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Current user is not a collaborator of journal " + journalId));

        if (currentUserCollaboration.getRole() != CollaboratorRole.OWNER) {
            throw new RuntimeException("Only journal owners can add collaborators");
        }


        var collaboratorUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found " + request.getUserId()));


        boolean exists = journalCollaboratorRepository.existsByJournalIdAndUserId(journalId, collaboratorUser.getId());
        if (exists) {
            throw new RuntimeException("User is already a collaborator of the journal");
        }


        JournalCollaborator journalCollaborator = new JournalCollaborator();
        journalCollaborator.setJournal(journal);
        journalCollaborator.setUser(collaboratorUser);


        journalCollaborator.setRole(CollaboratorRole.valueOf(request.getRole().name()));


        var saved = journalCollaboratorRepository.save(journalCollaborator);


        var response = new CollaboratorResponse();
        response.setJournalId(journalId);
        response.setUserId(saved.getUser().getId());
        response.setRole(request.getRole()); // sau mapezi din saved -> generated
        response.setCreatedAt(saved.getCreatedDate());
        response.setUpdatedAt(saved.getUpdatedDate());

        return response;
    }

    public List<CollaboratorResponse> getJournalCollaborators(Long journalId) {

        var currentUserId = currentUserProvider.getCurrentUserId();


        var journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));


        Visibility visibility = journal.getVisibility();

        if (visibility != Visibility.PUBLIC) {
            boolean isCollaborator = journalCollaboratorRepository
                    .existsByJournalIdAndUserId(journalId, currentUserId);

            if (!isCollaborator) {
                throw new RuntimeException("Forbidden: you are not a collaborator of journal " + journalId);
            }
        }


        var collaborators = journalCollaboratorRepository.findByJournalId(journalId);


        return collaborators.stream()
                .map(c -> {
                    var r = new CollaboratorResponse();
                    r.setJournalId(journalId);
                    r.setUserId(c.getUser().getId());
                    r.setRole(org.example.travel_journal_app.generated.model.CollaboratorRole.valueOf(
                            c.getRole().name()
                    ));

                    r.setCreatedAt(c.getCreatedDate());
                    r.setUpdatedAt(c.getUpdatedDate());

                    return r;
                })
                .toList();
    }


    public void deleteJournalCollaborator(Long journalId , Long collaboratorUserId) {

        var currentUserId = currentUserProvider.getCurrentUserId();

        journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));

        var currentUserCollaboration = journalCollaboratorRepository.findByJournalIdAndUserId(journalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Current user is not a collaborator of journal " + journalId));

        if (currentUserCollaboration.getRole() != CollaboratorRole.OWNER) {
            throw new RuntimeException("Only journal owners can remove collaborators");
        }

        var target = journalCollaboratorRepository.findByJournalIdAndUserId(journalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));

        if(target.getRole() == CollaboratorRole.OWNER) {
        long ownerCount = journalCollaboratorRepository.findByJournalId(journalId)
                .stream()
                .filter(c -> c.getRole() == CollaboratorRole.OWNER)
                .count();

        if(ownerCount <= 1) {
        throw new RuntimeException("Cannot remove the last owner of the journal");}
        }
        journalCollaboratorRepository.delete(target);
    }

    public CollaboratorResponse replaceJournalCollaborator (Long journalId,
                                                            Long collaboratorUserId,
                                                            CollaboratorPutRequest request) {

        var currentUserId = currentUserProvider.getCurrentUserId();

        journalRepository.findById(journalId)
                .orElseThrow(() -> new RuntimeException("Journal not found " + journalId));
        var currentUserCollaboration = journalCollaboratorRepository.findByJournalIdAndUserId(journalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Current user is not a collaborator of journal" + journalId));
        if (currentUserCollaboration.getRole() != CollaboratorRole.OWNER) {
            throw new RuntimeException("Only journal owners can update collaborators");
        }

        var target = journalCollaboratorRepository.findByJournalIdAndUserId(journalId, collaboratorUserId)
                .orElseThrow(() -> new RuntimeException("Collaborator not found for user " + collaboratorUserId + " in journal " + journalId));

        if(request.getRole() == null){
            throw new RuntimeException("Role must be provided");
        }

        CollaboratorRole newRole = CollaboratorRole.valueOf(request.getRole().name());
        CollaboratorRole oldRole = target.getRole();

        if(oldRole == CollaboratorRole.OWNER && newRole != CollaboratorRole.OWNER) {
            long ownerCount = journalCollaboratorRepository.findByJournalId(journalId).stream()
                    .filter(c -> c.getRole() == CollaboratorRole.OWNER)
                    .count();

            if(ownerCount <= 1) {
                throw new RuntimeException("Cannot change role of the last owner of the journal"); }
        }

        target.setRole(newRole);
        var response = new CollaboratorResponse();
        response.setJournalId(journalId);
        response.setUserId(target.getUser().getId());
        response.setRole(request.getRole());
        response.setCreatedAt(target.getCreatedDate());
        response.setUpdatedAt(target.getUpdatedDate());

        return response;
    }
}
