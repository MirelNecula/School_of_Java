package org.example.travel_journal_app.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tags", uniqueConstraints = {
        @UniqueConstraint(name="uk_tags_user_normalized" , columnNames =  {"user_id", "normalized_name"})
})
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "normalized_name", nullable = false, length = 50)
    private String normalizedName;

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

    public static String normalize(String s) {
        return s == null ? null : s.trim().toLowerCase();
    }
}
