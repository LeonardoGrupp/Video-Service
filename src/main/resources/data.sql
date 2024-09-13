INSERT INTO video_artists (id, name)
VALUES
    -- VIDEO
        (1, 'Iron Maiden'),
        (2, 'Dire Straits')
;

INSERT INTO video_albums (id, album_name, release_date)
VALUES
    (1, 'Rock in Rio', '2002'),
    (2, 'Alchemy', '1984')
;

-- INSERT Genres
INSERT INTO video_genres (id, genre, total_likes, total_plays)
VALUES
    (1, 'Rock', 0, 0),
    (2, 'Heavy Metal', 0, 0)
;

INSERT INTO videos (id, type, title, url, release_date, play_counter, likes, dis_likes)
VALUES
    -- MUSIC
    (1, 'video', 'The Wicker Man', 'url39', '2002', 0, 0, 0),
    (2, 'video','Ghost Of the Navigator', 'url40', '2002', 0, 0, 0),
    (3, 'video', 'Brave New World', 'url41', '2002', 0, 0, 0),
    (4, 'video', 'Wrathchild', 'url42', '2002', 0, 0, 0),
    (5, 'video', '2 Minutes To Midnight', 'url43', '2002', 0, 0, 0),
    (6, 'video', 'Blood Brothers', 'url44', '2002', 0, 0, 0),
    (7, 'video', 'Sign Of the Cross', 'url45', '2002', 0, 0, 0),
    (8, 'video', 'The Mercenary', 'url46', '2002', 0, 0, 0),
    (9, 'video', 'The Trooper', 'url47', '2002', 0, 0, 0),
    (10, 'video', 'Dream Of Mirrors', 'url48', '2002', 0, 0, 0),
    (11, 'video', 'The Clansman', 'url49', '2002', 0, 0, 0),
    (12, 'video', 'The Evil That Men Do', 'url50', '2002', 0, 0, 0),
    (13, 'video', 'Fear Of the Dark', 'url51', '2002', 0, 0, 0),
    (14, 'video', 'Iron Maiden', 'url52', '2002', 0, 0, 0),
    (15, 'video', 'The Number Of the Beast', 'url53', '2002', 0, 0, 0),
    (16, 'video', 'Hallowed Be Thy Name', 'url54', '2002', 0, 0, 0),
    (17, 'video', 'Sanctuary', 'url55', '2002', 0, 0, 0),
    (18, 'video', 'Run To the Hills', 'url56', '2002', 0, 0, 0),

    (19, 'video', 'Once Upon A Time in the West', 'url57', '1984', 0, 0, 0),
    (20, 'video', 'Romeo And Juliet', 'url58', '1984', 0, 0, 0),
    (21, 'video', 'Expresso Love', 'url59', '1984', 0, 0, 0),
    (22, 'video', 'Private Investigations', 'url60', '1984', 0, 0, 0),
    (23, 'video', 'Sultans Of Swing', 'url61', '1984', 0, 0, 0),
    (24, 'video', 'Tunnel Of Love', 'url62', '1984', 0, 0, 0),
    (25, 'video', 'Telegraph Road', 'url63', '1984', 0, 0, 0),
    (26, 'video', 'Solid Rock', 'url64', '1984', 0, 0, 0),
    (27, 'video', 'Going Home - Theme From ''Local Hero''', 'url65', '1984', 0, 0, 0)
;

INSERT INTO videos_genres (videos_id, genre_id)
VALUES
    -- MUSIC
    -- Iron Maiden to Metal
    (1, 2), (2, 2), (3, 2), (4, 2), (5, 2),
    (6, 2), (7, 2), (8, 2), (9, 2), (10, 2),
    (11, 2), (12, 2), (13, 2), (14, 2), (15, 2),
    (16, 2), (17, 2), (18, 2),

    --Dire Straits
    (19, 1), (20, 1), (21, 1), (22, 1), (23, 1),
    (24, 1), (25, 1), (26, 1), (27, 1)
;

-- INSERT Artists
INSERT INTO videos_artists (videos_id, artist_id)
VALUES
    -- MUSIC
    --Come Clarity
    (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), -- Iron Maiden
    (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), -- Iron Maiden
    (13, 1), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), -- Iron Maiden
    -- Features
    --(1, 2, 'Lisa Miskovsky'),

    --Rated R
    (19, 2), (20, 2), (21, 2), (22, 2), -- Dire Straits
    (23, 2), (24, 2), (25, 2), (26, 2), -- Dire Straits
    (27, 2)
    -- Features
    --(19, 5, 'Jeezy'),
;

-- INSERT ALbums
INSERT INTO videos_albums (videos_id, album_id)
VALUES
    -- VIDEO
    -- Iron Maiden
    (1, 1), -- Rock in Rio
    (2, 1), -- Rock in Rio
    (3, 1), -- Rock in Rio
    (4, 1), -- Rock in Rio
    (5, 1), -- Rock in Rio
    (6, 1), -- Rock in Rio
    (7, 1), -- Rock in Rio
    (8, 1), -- Rock in Rio
    (9, 1), -- Rock in Rio
    (10, 1), -- Rock in Rio
    (11, 1), -- Rock in Rio
    (12, 1), -- Rock in Rio
    (13, 1), -- Rock in Rio
    (14, 1), -- Rock in Rio
    (15, 1), -- Rock in Rio
    (16, 1), -- Rock in Rio
    (17, 1), -- Rock in Rio
    (18, 1), -- Rock in Rio

    -- Dire Straits
    (19, 2), (20, 2), (21, 2), (22, 2), -- Alchemy
    (23, 2), (24, 2), (25, 2), (26, 2), -- Alchemy
    (27, 2) -- Alchemy
;