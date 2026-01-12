package org.example.travel_journal_app.service;

import org.example.travel_journal_app.entities.Location;
import org.example.travel_journal_app.exception.DuplicateResourceException;
import org.example.travel_journal_app.generated.model.JournalEntryResponse;
import org.example.travel_journal_app.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Fac DTO-uri aici

    public record CreateLocationRequest(
            String country,
            String city,
            Double latitude,
            Double longitude
    ) {}

    public record UpdateLocationRequest(
            String country,
            String city,
            Double latitude,
            Double longitude
    ){}

    public record LocationResponse(
            Long id,
            String country,
            String city,
            Double latitude,
            Double longitude
    ) {}

    public LocationResponse create(CreateLocationRequest req) {
        if (req.country == null || req.country.isEmpty()) {
            throw new DuplicateResourceException("country is required ");
        }
        if (req.city == null || req.city.isEmpty()) {
            throw new DuplicateResourceException("city is required ");
        }
        if (req.latitude == null || req.longitude == null) {
            throw new DuplicateResourceException("latitude and longitude are required ");
        }


        Location l = new Location();
        l.setCountry(req.country());
        l.setCity(req.city());
        l.setLatitude(req.latitude());
        l.setLongitude(req.longitude());
        Location saved = locationRepository.save(l);
        return toResponse(l);
    }


    public LocationResponse getById(Long id) {
        Location l = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found" + id));
        return toResponse(l);
    }

    public LocationResponse update(Long id, UpdateLocationRequest req) {
        Location l = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found" + id));

        if (req.country() == null || req.country().isBlank()) {
            throw new IllegalArgumentException("country is required");
        }
        if (req.city() == null || req.city().isBlank()) {
            throw new IllegalArgumentException("city is required");
        }
        if (req.latitude() == null || req.longitude() == null) {
            throw new IllegalArgumentException("latitude & longitude are required");
        }

        l.setCountry(req.country());
        l.setCity(req.city());
        l.setLatitude(req.latitude());
        l.setLongitude(req.longitude());
        locationRepository.save(l);
        return toResponse(l);
    }

    public void delete(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location not found" + id);
        }
        locationRepository.deleteById(id);
    }

    private LocationResponse toResponse(Location l) {
        return new LocationResponse(
                l.getId(),
                l.getCountry(),
                l.getCity(),
                l.getLatitude(),
                l.getLongitude()
        );
    }
}
