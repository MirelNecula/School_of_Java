package org.example.travel_journal_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "journal_collaborators", uniqueConstraints = @UniqueConstraint(columnNames = {"journal_id", "user_id"}))
@Getter
@Setter
public class JournalCollaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private CollaboratorRole role;

    @Column(name="created_at" , nullable = false, updatable = false)
    private OffsetDateTime createdDate;

    @Column(name="updated_at" , nullable = false)
    private OffsetDateTime updatedDate;

    @PrePersist
    void onCreate() {
        this.createdDate = OffsetDateTime.now();
        this.updatedDate = this.createdDate;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedDate = OffsetDateTime.now();
    }
}
