Database schema


1️⃣ ENUM Types (Create FIRST)
CREATE TYPE user_role AS ENUM (
    'ADMIN',
    'USER'
);

CREATE TYPE content_type AS ENUM (
    'MOVIE',
    'SERIES',
    'ANIME'
);

CREATE TYPE content_status AS ENUM (
    'ONGOING',
    'COMPLETED'
);

CREATE TYPE review_type AS ENUM (
    'SKIP',
    'TIMEPASS',
    'GO_FOR_IT',
    'PERFECTION'
);

2️⃣ USERS Table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    bio TEXT,
    avatar_image BYTEA,
    role user_role NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


hi 
3️⃣ CONTENT Table (Movie / Series / Anime)
CREATE TABLE content (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content_type content_type NOT NULL,
    description TEXT,
    release_date DATE,
    poster_image BYTEA,
    duration_minutes INT,
    seasons_count INT,
    status content_status,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

4️⃣ GENRES Table
CREATE TABLE genres (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

5️⃣ CONTENT ↔ GENRES (Many-to-Many)
CREATE TABLE content_genres (
    content_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (content_id, genre_id),
    CONSTRAINT fk_content_genre_content
        FOREIGN KEY (content_id) REFERENCES content(id) ON DELETE CASCADE,
    CONSTRAINT fk_content_genre_genre
        FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

6️⃣ PLATFORMS Table
CREATE TABLE platforms (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

7️⃣ CONTENT ↔ PLATFORMS (Many-to-Many)
CREATE TABLE content_platforms (
    content_id BIGINT NOT NULL,
    platform_id BIGINT NOT NULL,
    PRIMARY KEY (content_id, platform_id),
    CONSTRAINT fk_content_platform_content
        FOREIGN KEY (content_id) REFERENCES content(id) ON DELETE CASCADE,
    CONSTRAINT fk_content_platform_platform
        FOREIGN KEY (platform_id) REFERENCES platforms(id) ON DELETE CASCADE
);

8️⃣ REVIEWS Table
CREATE TABLE reviews (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content_id BIGINT NOT NULL,
    review_type review_type NOT NULL,
    comment_text TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_review_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_review_content
        FOREIGN KEY (content_id) REFERENCES content(id) ON DELETE CASCADE,
    CONSTRAINT uq_user_content_review
        UNIQUE (user_id, content_id)
);

9️⃣ REVIEW LIKES Table
CREATE TABLE review_likes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    review_id BIGINT NOT NULL,

    CONSTRAINT fk_review_like_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_review_like_review
        FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE,
    CONSTRAINT uq_user_review_like
        UNIQUE (user_id, review_id)
);

🔟 REVIEW COMMENTS Table
CREATE TABLE review_comments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    review_id BIGINT NOT NULL,
    comment_text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_review_comment_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_review_comment_review
        FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE
);

1️⃣1️⃣ Indexes (Performance-Critical)
-- Search
CREATE INDEX idx_content_title ON content(title);

-- Filtering
CREATE INDEX idx_content_type ON content(content_type);

-- Reviews
CREATE INDEX idx_reviews_content ON reviews(content_id);
CREATE INDEX idx_reviews_user ON reviews(user_id);
CREATE INDEX idx_reviews_type ON reviews(review_type);

-- Leaderboards
CREATE INDEX idx_review_likes_review ON review_likes(review_id);