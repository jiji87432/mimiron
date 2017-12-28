DROP TABLE IF EXISTS mmr_user;
CREATE TABLE mmr_user (
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    login              VARCHAR(50)           NOT NULL,
    password_hash      VARCHAR(60),
    first_name         VARCHAR(50),
    last_name          VARCHAR(50),
    email              VARCHAR(100),
    image_url          VARCHAR(256),
    activated          BOOLEAN               NOT NULL,
    lang_key           VARCHAR(6),
    activation_key     VARCHAR(20),
    reset_key          VARCHAR(20),
    created_by         VARCHAR(50)           NOT NULL,
    created_date       TIMESTAMP,
    reset_date         TIMESTAMP,
    last_modified_by   VARCHAR(50),
    last_modified_date TIMESTAMP,
    CONSTRAINT PK_MMR_USER PRIMARY KEY (id),
    CONSTRAINT ux_user_login UNIQUE (login),
    CONSTRAINT ux_user_email UNIQUE (email)
);
CREATE UNIQUE INDEX idx_user_login
    ON mmr_user (login);
CREATE UNIQUE INDEX idx_user_email
    ON mmr_user (email);

DROP TABLE IF EXISTS mmr_authority;
CREATE TABLE mmr_authority (
    name VARCHAR(50) NOT NULL,
    CONSTRAINT PK_MMR_AUTHORITY PRIMARY KEY (name)
);
DROP TABLE IF EXISTS mmr_user_authority;
CREATE TABLE mmr_user_authority (
    user_id        BIGINT      NOT NULL,
    authority_name VARCHAR(50) NOT NULL
);
ALTER TABLE mmr_user_authority
    ADD PRIMARY KEY (user_id, authority_name);
ALTER TABLE mmr_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES mmr_authority (name);
ALTER TABLE mmr_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES mmr_user (id);

DROP TABLE IF EXISTS mmr_persistent_audit_event;
CREATE TABLE mmr_persistent_audit_event (
    event_id   BIGINT AUTO_INCREMENT NOT NULL,
    principal  VARCHAR(50)           NOT NULL,
    event_date TIMESTAMP,
    event_type VARCHAR(255),
    CONSTRAINT PK_MMR_PERSISTENT_AUDIT_EVENT PRIMARY KEY (event_id)
);
DROP TABLE IF EXISTS mmr_persistent_audit_evt_data;
CREATE TABLE mmr_persistent_audit_evt_data (
    event_id BIGINT       NOT NULL,
    name     VARCHAR(150) NOT NULL,
    value    VARCHAR(255)
);
ALTER TABLE mmr_persistent_audit_evt_data
    ADD PRIMARY KEY (event_id, name);
CREATE INDEX idx_persistent_audit_event
    ON mmr_persistent_audit_event (principal, event_date);
CREATE INDEX idx_persistent_audit_evt_data
    ON mmr_persistent_audit_evt_data (event_id);
ALTER TABLE mmr_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES mmr_persistent_audit_event (event_id);


