--
-- Assumptions:
-- The target database already exists.
-- Flyway will create the schema in the target database (see spring.flyway.default-schema)
--

CREATE TABLE IF NOT EXISTS yb_yftt_demo
(
    id           uuid PRIMARY KEY,
    message      text        NOT NULL,
    created_date timestamptz NOT NULL DEFAULT current_timestamp,
    updated_date timestamptz NOT NULL DEFAULT current_timestamp
);