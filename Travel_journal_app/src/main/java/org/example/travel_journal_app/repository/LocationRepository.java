package org.example.travel_journal_app.repository;


import org.example.travel_journal_app.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository  extends JpaRepository<Location, Long> {

}
