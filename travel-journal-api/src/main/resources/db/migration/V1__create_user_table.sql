CREATE TABLE "user"(
    user_id    SERIAL        NOT NULL PRIMARY KEY,
    email     VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(256) NOT NULL,
    name      VARCHAR(100) NOT NULL,
    surname    VARCHAR(100) NOT NULL
)