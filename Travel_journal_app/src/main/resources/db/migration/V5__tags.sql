CREATE TABLE tags (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    normalized_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT fk_tags_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT uk_tags_user_normalized UNIQUE (user_id, normalized_name)
);

CREATE TABLE journal_entry_tags (
    journal_entry_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (journal_entry_id, tag_id),
    CONSTRAINT fk_jet_entry FOREIGN KEY (journal_entry_id) REFERENCES journal_entries(id) ON DELETE CASCADE,
    CONSTRAINT fk_jet_tag FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);
