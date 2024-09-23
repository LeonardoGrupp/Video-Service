CREATE TABLE IF NOT EXISTS videos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    title VARCHAR(250) NOT NULL,
    url VARCHAR(250) NOT NULL,
    release_date DATE NOT NULL,
    play_counter INT DEFAULT 0,
    likes INT DEFAULT 0,
    dis_likes INT DEFAULT 0
    )
;

CREATE TABLE IF NOT EXISTS genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(255) NOT NULL,
    total_likes INT DEFAULT 0,
    total_plays INT DEFAULT 0
    )
;

CREATE TABLE IF NOT EXISTS videos_genres (
    videos_id INT,
    genre_id INT,
    FOREIGN KEY(videos_id) REFERENCES videos(id),
    FOREIGN KEY(genre_id) REFERENCES genres(id)
    )
;

CREATE TABLE IF NOT EXISTS videos_artists (
    videos_id INT,
    artist_id INT,
    FOREIGN KEY(videos_id) REFERENCES videos(id),
    FOREIGN KEY(artist_id) REFERENCES artist(id)
    )
;

CREATE TABLE IF NOT EXISTS videos_albums (
    videos_id INT,
    album_id INT,
    FOREIGN KEY(videos_id) REFERENCES videos(id),
    FOREIGN KEY(album_id) REFERENCES album(id)
    )
;

-- INSERT Genres
INSERT INTO genres (id, genre, total_likes, total_plays)
VALUES
    (19, 'Rock', 0, 0),
    (20, 'Heavy Metal', 0, 0)
;

INSERT INTO videos (id, type, title, url, release_date, play_counter, likes, dis_likes)
VALUES
    (1, 'video', 'The Wicker Man', 'url39', '2002', 0, 0, 0), (2, 'video','Ghost Of the Navigator', 'url40', '2002', 0, 0, 0), (3, 'video', 'Brave New World', 'url41', '2002', 0, 0, 0), (4, 'video', 'Wrathchild', 'url42', '2002', 0, 0, 0), (5, 'video', '2 Minutes To Midnight', 'url43', '2002', 0, 0, 0), (6, 'video', 'Blood Brothers', 'url44', '2002', 0, 0, 0), (7, 'video', 'Sign Of the Cross', 'url45', '2002', 0, 0, 0), (8, 'video', 'The Mercenary', 'url46', '2002', 0, 0, 0), (9, 'video', 'The Trooper', 'url47', '2002', 0, 0, 0), (10, 'video', 'Dream Of Mirrors', 'url48', '2002', 0, 0, 0), (11, 'video', 'The Clansman', 'url49', '2002', 0, 0, 0), (12, 'video', 'The Evil That Men Do', 'url50', '2002', 0, 0, 0), (13, 'video', 'Fear Of the Dark', 'url51', '2002', 0, 0, 0), (14, 'video', 'Iron Maiden', 'url52', '2002', 0, 0, 0), (15, 'video', 'The Number Of the Beast', 'url53', '2002', 0, 0, 0), (16, 'video', 'Hallowed Be Thy Name', 'url54', '2002', 0, 0, 0), (17, 'video', 'Sanctuary', 'url55', '2002', 0, 0, 0), (18, 'video', 'Run To the Hills', 'url56', '2002', 0, 0, 0),

    (19, 'video', 'Once Upon A Time in the West', 'url57', '1984', 0, 0, 0), (20, 'video', 'Romeo And Juliet', 'url58', '1984', 0, 0, 0), (21, 'video', 'Expresso Love', 'url59', '1984', 0, 0, 0), (22, 'video', 'Private Investigations', 'url60', '1984', 0, 0, 0), (23, 'video', 'Sultans Of Swing', 'url61', '1984', 0, 0, 0), (24, 'video', 'Tunnel Of Love', 'url62', '1984', 0, 0, 0), (25, 'video', 'Telegraph Road', 'url63', '1984', 0, 0, 0), (26, 'video', 'Solid Rock', 'url64', '1984', 0, 0, 0), (27, 'video', 'Going Home - Theme From ''Local Hero''', 'url65', '1984', 0, 0, 0);
;

INSERT INTO videos_genres (videos_id, genre_id)
VALUES
    -- VIDEOS
    -- Iron Maiden to Metal
    (1, 20), (2, 20), (3, 20), (4, 20), (5, 20), (6, 20), (7, 20), (8, 20), (9, 20), (10, 20), (11, 20), (12, 20), (13, 20), (14, 20), (15, 20), (16, 20), (17, 20), (18, 20),

    --Dire Straits
    (19, 19), (20, 19), (21, 19), (22, 19), (23, 19), (24, 19), (25, 19), (26, 19), (27, 19)
;

-- INSERT Artists
INSERT INTO videos_artists (videos_id, artist_id)
VALUES
    -- VIDEOS
    --Rock in Rio
    (1, 9), (2, 9), (3, 9), (4, 9), (5, 9), (6, 9), (7, 9), (8, 9), (9, 9), (10, 9), (11, 9), (12, 9), (13, 9), (14, 9), (15, 9), (16, 9), (17, 9), (18, 9), -- Iron Maiden

    --Alchemy
    (19, 10), (20, 10), (21, 10), (22, 10), (23, 10), (24, 10), (25, 10), (26, 10), (27, 10) -- Dire Straits
;

-- INSERT ALbums
INSERT INTO videos_albums (videos_id, album_id)
VALUES
    -- VIDEO
    -- Iron Maiden
    (1, 4), (2, 4), (3, 4), (4, 4), (5, 4), (6, 4), (7, 4), (8, 4), (9, 4), (10, 4), (11, 4), (12, 4), (13, 4), (14, 4), (15, 4), (16, 4), (17, 4), (18, 4), -- Rock in Rio

    -- Dire Straits
    (19, 5), (20, 5), (21, 5), (22, 5), (23, 5), (24, 5), (25, 5), (26, 5), (27, 5) -- Alchemy
;