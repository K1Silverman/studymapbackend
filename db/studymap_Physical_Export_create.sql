CREATE TABLE "attachments" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "body" bytea NOT NULL,
  "post_id" int NOT NULL
);

CREATE TABLE "posts" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "body" varchar(500) NOT NULL,
  "timestamp" timestamp NOT NULL,
  "subject_id" int NOT NULL,
  "attachments_id" int NOT NULL,
  "owner_id" int NOT NULL,
  "active" boolean NOT NULL,
  "users_id" int NOT NULL
);

CREATE TABLE "subject" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "name" varchar(500) NOT NULL,
  "theme_id" int NOT NULL,
  "active" boolean NOT NULL
);

CREATE TABLE "themes" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "name" varchar(200) NOT NULL DEFAULT 'Default Theme',
  "first_color" varchar(50),
  "secondary_color" varchar(50),
  "buttons_color" varchar(50)
);

CREATE TABLE "folders" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "foldname" varchar(200) NOT NULL DEFAULT 'New Folder',
  "user_id" int NOT NULL
);

CREATE TABLE "folders_content" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "folder_id" int NOT NULL,
  "post_id" int NOT NULL
);

CREATE TABLE "users" (
  "id" INT GENERATED BY DEFAULT AS IDENTITY UNIQUE PRIMARY KEY,
  "firstname" varchar(200) NOT NULL,
  "lastname" varchar(200) NOT NULL,
  "pw" varchar(200) NOT NULL,
  "email" varchar(200) NOT NULL,
  "role" varchar(20) NOT NULL,
  "status" varchar(50) NOT NULL
);

ALTER TABLE "attachments" ADD CONSTRAINT "attachments_to_post_posts" FOREIGN KEY ("post_id") REFERENCES "posts" ("id");

ALTER TABLE "posts" ADD CONSTRAINT "posts_subject" FOREIGN KEY ("subject_id") REFERENCES "subject" ("id");

ALTER TABLE "folders" ADD CONSTRAINT "folders_users" FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "folders_content" ADD CONSTRAINT "posts_to_users_by_folders" FOREIGN KEY ("folder_id") REFERENCES "folders" ("id");

ALTER TABLE "folders_content" ADD CONSTRAINT "users_to_posts_by_folders" FOREIGN KEY ("post_id") REFERENCES "posts" ("id");

ALTER TABLE "subject" ADD CONSTRAINT "theme_to_subject" FOREIGN KEY ("theme_id") REFERENCES "themes" ("id");
