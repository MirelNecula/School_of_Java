package org.example.travel_journal_app.service;

import jakarta.transaction.Transactional;
import org.example.travel_journal_app.entities.Tag;
import org.example.travel_journal_app.entities.User;
import org.example.travel_journal_app.generated.model.TagCreateRequest;
import org.example.travel_journal_app.generated.model.TagPatchRequest;
import org.example.travel_journal_app.generated.model.TagPutRequest;
import org.example.travel_journal_app.generated.model.TagResponse;
import org.example.travel_journal_app.repository.JournalEntryRepository;
import org.example.travel_journal_app.repository.TagRepository;
import org.example.travel_journal_app.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final CurrentUserProvider currentUserProvider;

    public TagService(TagRepository tagRepository, JournalEntryRepository journalEntryRepository, CurrentUserProvider currentUserProvider) {
        this.tagRepository = tagRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public TagResponse create(TagCreateRequest req) {
        Long userId = currentUserProvider.getCurrentUserId();

        String name = req.getName();
        String norm = Tag.normalize(name);

        if (norm == null || norm.isBlank()) {
            throw new RuntimeException("Tag name is required");
        }
        if (tagRepository.existsByUser_IdAndNormalizedName(userId, norm)) {
            throw new RuntimeException("Tag with the same name already exists");
        }

        Tag tag = new Tag();

        User u = new User();
        u.setId(userId);
        tag.setUser(u);

        tag.setName(name.trim());
        tag.setNormalizedName(norm);

        return toResponse(tagRepository.save(tag));
    }

    public List<TagResponse> getAll() {
        Long userId = currentUserProvider.getCurrentUserId();
        return tagRepository.findAllByUser_Id(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TagResponse getById(Long tagId) {
        Long userId = currentUserProvider.getCurrentUserId();
        Tag tag = tagRepository.findByIdAndUser_Id(tagId, userId)
                .orElseThrow(() -> new RuntimeException("Tag not found " + tagId));
        return toResponse(tag);
    }

    public TagResponse replace(Long tagId, TagPutRequest req) {
        Long userId = currentUserProvider.getCurrentUserId();
        Tag tag = tagRepository.findByIdAndUser_Id(tagId, userId)
                .orElseThrow(() -> new RuntimeException("Tag not found " + tagId));

        String norm = Tag.normalize(req.getName());
        if (norm == null || norm.isBlank()) {
            throw new RuntimeException("Tag name is required");
        }

        if (!norm.equals(tag.getNormalizedName())
                && tagRepository.existsByUser_IdAndNormalizedName(userId, norm)) {
            throw new RuntimeException("Tag with the same name already exists");
        }

        tag.setName(req.getName().trim());
        tag.setNormalizedName(norm);

        return toResponse(tagRepository.save(tag));
    }

    public TagResponse patch(Long tagId, TagPatchRequest req) {
        Long userId = currentUserProvider.getCurrentUserId();
        Tag tag = tagRepository.findByIdAndUser_Id(tagId, userId)
                .orElseThrow(() -> new RuntimeException("Tag not found " + tagId));

        if (req.getName() != null) {
            String norm = Tag.normalize(req.getName());

            if (norm == null || norm.isBlank()) {
                throw new RuntimeException("Tag name is required");
            }

            if (!norm.equals(tag.getNormalizedName())
                    && tagRepository.existsByUser_IdAndNormalizedName(userId, norm)) {
                throw new RuntimeException("Tag with the same name already exists");
            }

            tag.setName(req.getName().trim());
            tag.setNormalizedName(norm);
        }

        return toResponse(tagRepository.save(tag));
    }

    public void delete(Long tagId) {
        Long userId = currentUserProvider.getCurrentUserId();
        Tag tag = tagRepository.findByIdAndUser_Id(tagId, userId)
                .orElseThrow(() -> new RuntimeException("Tag not found " + tagId));
        tagRepository.delete(tag);
    }

    @Transactional
    public List<TagResponse> getTagsForEntry(Long journalId, Long entryId) {
        var entry = journalEntryRepository.findByIdAndJournal_Id(journalId, entryId)
                .orElseThrow(() -> new RuntimeException(
                        "Journal Entry not found " + entryId + " for Journal " + journalId));

        return entry.getTags()
                .stream()
                .map(tag -> new TagResponse()
                        .id(tag.getId())
                .name(tag.getName()))
                .toList();
    }

    @Transactional
    public void addTagToEntry(Long journalId, Long entryId, Long tagId) {
        Long userId = currentUserProvider.getCurrentUserId();

        var entry = journalEntryRepository.findByIdAndJournal_Id(entryId, journalId)
                .orElseThrow(() -> new RuntimeException(
                        "Journal Entry not found " + entryId + " for Journal " + journalId));

        Tag tag = tagRepository.findByIdAndUser_Id(tagId, userId)
                .orElseThrow(() -> new RuntimeException("Tag not found " + tagId));

        entry.getTags().add(tag);
        journalEntryRepository.save(entry);
    }
    @Transactional
    public void removeTagFromEntry(Long journalId, Long entryId, Long tagId) {
        Long userId = currentUserProvider.getCurrentUserId();

        var entry = journalEntryRepository.findByIdAndJournal_Id(entryId, journalId)
                .orElseThrow(() -> new RuntimeException(
                        "Journal Entry not found " + entryId + " for Journal " + journalId));

        Tag tag = tagRepository.findByIdAndUser_Id(tagId, userId)
                .orElseThrow(() -> new RuntimeException("Tag not found " + tagId));

        entry.getTags().remove(tag);
        journalEntryRepository.save(entry);
    }

    private TagResponse toResponse(Tag tag) {
        return new TagResponse()
                .id(tag.getId())
                .name(tag.getName());
    }
}
