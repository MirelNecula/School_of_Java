CREATE TABLE journal_collaborators (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    journal_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    role VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_journal_collaborators_journal
        FOREIGN KEY (journal_id) REFERENCES journals(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_journal_collaborators_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_journal_collaborators_journal_user
        UNIQUE (journal_id, user_id),

    CONSTRAINT ck_journal_collaborators_role
        CHECK (role IN ('OWNER', 'EDITOR', 'VIEWER'))
);

CREATE INDEX idx_journal_collaborators_user_id
    ON journal_collaborators(user_id);

CREATE INDEX idx_journal_collaborators_journal_id
    ON journal_collaborators(journal_id);