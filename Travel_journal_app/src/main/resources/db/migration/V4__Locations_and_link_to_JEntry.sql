
CREATE TABLE locations (
    id BIGSERIAL PRIMARY KEY,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,


    CONSTRAINT chk_locations_latitude CHECK (latitude >= -90 AND latitude <= 90),
    CONSTRAINT chk_locations_longitude CHECK (longitude >= -180 AND longitude <= 180)
);


ALTER TABLE journal_entries
    ADD COLUMN location_id BIGINT;

ALTER TABLE journal_entries
    ADD CONSTRAINT fk_journal_entries_location
        FOREIGN KEY (location_id)
        REFERENCES locations(id);


CREATE INDEX idx_journal_entries_location_id ON journal_entries(location_id);
