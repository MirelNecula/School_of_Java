package org.example.travel_journal_app.api.controller;

import org.example.travel_journal_app.generated.api.TagsApi;
import org.example.travel_journal_app.generated.model.TagCreateRequest;
import org.example.travel_journal_app.generated.model.TagPatchRequest;
import org.example.travel_journal_app.generated.model.TagPutRequest;
import org.example.travel_journal_app.generated.model.TagResponse;
import org.example.travel_journal_app.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController implements TagsApi {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public ResponseEntity<TagResponse> createTag(TagCreateRequest tagCreateRequest) {
        return ResponseEntity.status(201).body(tagService.create(tagCreateRequest));
    }

    @Override
    public ResponseEntity<List<TagResponse>> getAllTags() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @Override
    public ResponseEntity<TagResponse> getTagById(Long tagId) {
        return ResponseEntity.ok(tagService.getById(tagId));
    }

    @Override
    public ResponseEntity<TagResponse> replaceTag(Long tagId, TagPutRequest tagPutRequest) {
        return ResponseEntity.ok(tagService.replace(tagId, tagPutRequest));
    }

    @Override
    public ResponseEntity<TagResponse> patchTag(Long tagId, TagPatchRequest tagPatchRequest) {
        return ResponseEntity.ok(tagService.patch(tagId, tagPatchRequest));
    }

    @Override
    public ResponseEntity<Void> deleteTag(Long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> addTagToEntry(Long journalId, Long entryId, Long tagId) {
        tagService.addTagToEntry(journalId, entryId, tagId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeTagFromEntry(Long journalId, Long entryId, Long tagId) {
        tagService.removeTagFromEntry(journalId, entryId, tagId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<TagResponse>> getTagsForEntry(Long journalId, Long entryId) {
        return ResponseEntity.ok(tagService.getTagsForEntry(journalId, entryId));
    }

}
