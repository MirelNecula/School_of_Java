package org.example.travel_journal_app.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.travel_journal_app.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationService.LocationResponse> create (
            @RequestBody LocationService.CreateLocationRequest req) {
        return ResponseEntity.ok(locationService.create(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationService.LocationResponse> getById (
            @PathVariable long id) {
        return ResponseEntity.ok(locationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationService.LocationResponse> update(
            @PathVariable Long id,
            @RequestBody LocationService.UpdateLocationRequest req
    ) {
        return ResponseEntity.ok(locationService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
