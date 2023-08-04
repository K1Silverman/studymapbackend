-- https://dbdiagram.io/d/64c2307c02bd1c4a5ec8e869

-- tables
-- Table: attachments
CREATE TABLE attachments (
    id int  NOT NULL,
    body bytea  NOT NULL,
    posts_id int  NOT NULL,
    CONSTRAINT attachments_pk PRIMARY KEY (id)
);

-- Table: posts
CREATE TABLE posts (
    id int  NOT NULL,
    body varchar(500)  NOT NULL,
    timestamp timestamp  NOT NULL,
    subject_id int  NOT NULL,
    attachments_id int  NOT NULL,
    owner_id int  NOT NULL,
    active boolean  NOT NULL,
    users_id int  NOT NULL,
    CONSTRAINT posts_pk PRIMARY KEY (id)
);

-- Table: subject
CREATE TABLE subject (
    id int  NOT NULL,
    name int  NOT NULL,
    theme varchar(100)  NOT NULL,
    active boolean  NOT NULL,
    CONSTRAINT subject_pk PRIMARY KEY (id)
);

-- Table: users
CREATE TABLE users (
    id int  NOT NULL,
    username int  NOT NULL,
    pw int  NOT NULL,
    email int  NOT NULL,
    role int  NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: attachments_to_post_posts (table: attachments)
ALTER TABLE attachments ADD CONSTRAINT attachments_to_post_posts
    FOREIGN KEY (posts_id)
    REFERENCES posts (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: posts_subject (table: posts)
ALTER TABLE posts ADD CONSTRAINT posts_subject
    FOREIGN KEY (subject_id)
    REFERENCES subject (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: posts_users (table: posts)
ALTER TABLE posts ADD CONSTRAINT posts_users
    FOREIGN KEY (users_id)
    REFERENCES users (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

