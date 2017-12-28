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


