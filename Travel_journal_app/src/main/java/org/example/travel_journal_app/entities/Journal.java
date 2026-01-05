package org.example.travel_journal_app.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;


@Entity
@Table(name="journals")
@Getter
@Setter
public class Journal {

    @Id
    @Column(name = "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title" , nullable = false , length = 200)
    private String title;

    @Column(name = "description" , length = 1000)
    private String description;

    @Column(name = "start_date" , nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility" , nullable = false, length = 20)
    private Visibility visibility;

    @Column(name = "created_at" , nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at" , nullable = false)
    private OffsetDateTime updatedAt;

    public Journal() {
        // required by JPA
    }
}
