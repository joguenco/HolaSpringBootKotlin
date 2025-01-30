CREATE TABLE IF NOT EXISTS friends
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying NOT NULL,
    birth_date timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS skills
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    friend_id bigint NOT NULL,
    name character varying NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_skills_friends FOREIGN KEY (friend_id)
        REFERENCES friends (id) MATCH FULL
        ON UPDATE RESTRICT
        ON DELETE CASCADE
        NOT VALID
);