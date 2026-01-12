package org.example.travel_journal_app.repository;

import org.example.travel_journal_app.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository  extends JpaRepository<Tag, Long> {

    List<Tag> findAllByUser_Id(Long userId);

    Optional<Tag> findByIdAndUser_Id(Long id, Long userId);

    boolean existsByUser_IdAndNormalizedName(Long userId, String normalizedName);

}
