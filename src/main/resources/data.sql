-- INSERT Genres
INSERT INTO video_genres (id, genre, total_likes, total_plays)
VALUES
    (1, 'Rock', 0, 0),
    (2, 'Heavy Metal', 0, 0)
;

INSERT INTO videos (id, type, title, url, release_date, play_counter, likes, dis_likes)
VALUES
    -- MUSIC
    (1, 'Video', 'The Wicker Man', 'url39', '2002', 0, 0, 0),
    (2, 'Video','Ghost Of the Navigator', 'url40', '2002', 0, 0, 0),
    (3, 'Video', 'Brave New World', 'url41', '2002', 0, 0, 0),
    (4, 'Video', 'Wrathchild', 'url42', '2002', 0, 0, 0),
    (5, 'Video', '2 Minutes To Midnight', 'url43', '2002', 0, 0, 0),
    (6, 'Video', 'Blood Brothers', 'url44', '2002', 0, 0, 0),
    (7, 'Video', 'Sign Of the Cross', 'url45', '2002', 0, 0, 0),
    (8, 'Video', 'The Mercenary', 'url46', '2002', 0, 0, 0),
    (9, 'Video', 'The Trooper', 'url47', '2002', 0, 0, 0),
    (10, 'Video', 'Dream Of Mirrors', 'url48', '2002', 0, 0, 0),
    (11, 'Video', 'The Clansman', 'url49', '2002', 0, 0, 0),
    (12, 'Video', 'The Evil That Men Do', 'url50', '2002', 0, 0, 0),
    (13, 'Video', 'Fear Of the Dark', 'url51', '2002', 0, 0, 0),
    (14, 'Video', 'Iron Maiden', 'url52', '2002', 0, 0, 0),
    (15, 'Video', 'The Number Of the Beast', 'url53', '2002', 0, 0, 0),
    (16, 'Video', 'Hallowed Be Thy Name', 'url54', '2002', 0, 0, 0),
    (17, 'Video', 'Sanctuary', 'url55', '2002', 0, 0, 0),
    (18, 'Video', 'Run To the Hills', 'url56', '2002', 0, 0, 0),

    (19, 'Video', 'Once Upon A Time in the West', 'url57', '1984', 0, 0, 0),
    (20, 'Video', 'Romeo And Juliet', 'url58', '1984', 0, 0, 0),
    (21, 'Video', 'Expresso Love', 'url59', '1984', 0, 0, 0),
    (22, 'Video', 'Private Investigations', 'url60', '1984', 0, 0, 0),
    (23, 'Video', 'Sultans Of Swing', 'url61', '1984', 0, 0, 0),
    (24, 'Video', 'Tunnel Of Love', 'url62', '1984', 0, 0, 0),
    (25, 'Video', 'Telegraph Road', 'url63', '1984', 0, 0, 0),
    (26, 'Video', 'Solid Rock', 'url64', '1984', 0, 0, 0),
    (27, 'Video', 'Going Home - Theme From ''Local Hero''', 'url65', '1984', 0, 0, 0);
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
INSERT INTO videos_artists (videos_id, id, name)
VALUES
    -- MUSIC
    --Come Clarity
    (1, 9, 'Iron Maiden'), (2, 9, 'Iron Maiden'), (3, 9, 'Iron Maiden'), (4, 9, 'Iron Maiden'), (5, 9, 'Iron Maiden'), (6, 9, 'Iron Maiden'),
    (7, 9, 'Iron Maiden'), (8, 9, 'Iron Maiden'), (9, 9, 'Iron Maiden'), (10, 9, 'Iron Maiden'), (11, 9, 'Iron Maiden'), (12, 9, 'Iron Maiden'),
    (13, 9, 'Iron Maiden'), (14, 9, 'Iron Maiden'), (15, 9, 'Iron Maiden'), (16, 9, 'Iron Maiden'), (17, 9, 'Iron Maiden'), (18, 9, 'Iron Maiden'),
    -- Features
    --(1, 2, 'Lisa Miskovsky'),

    --Rated R
    (19, 10, 'Dire Straits'), (20, 10, 'Dire Straits'), (21, 10, 'Dire Straits'), (22, 10, 'Dire Straits'),
    (23, 10, 'Dire Straits'), (24, 10, 'Dire Straits'), (25, 10, 'Dire Straits'), (26, 10, 'Dire Straits'),
    (27, 10, 'Dire Straits')
    -- Features
    --(19, 5, 'Jeezy'),
;

-- INSERT ALbums
INSERT INTO videos_albums (videos_id, id, name)
VALUES
    -- VIDEO
    -- Iron Maiden
    (1, 4, 'Rock in Rio'), (2, 4, 'Rock in Rio'), (3, 4, 'Rock in Rio'), (4, 4, 'Rock in Rio'), (5, 4, 'Rock in Rio'), (6, 4, 'Rock in Rio'),
    (7, 4, 'Rock in Rio'), (8, 4, 'Rock in Rio'), (9, 4, 'Rock in Rio'), (10, 4, 'Rock in Rio'), (11, 4, 'Rock in Rio'), (12, 4, 'Rock in Rio'),
    (13, 4, 'Rock in Rio'), (14, 4, 'Rock in Rio'), (15, 4, 'Rock in Rio'), (16, 4, 'Rock in Rio'), (17, 4, 'Rock in Rio'), (18, 4, 'Rock in Rio'),

    -- Dire Straits
    (19, 5, 'Alchemy'), (20, 5, 'Alchemy'), (21, 5, 'Alchemy'), (22, 5, 'Alchemy'),
    (23, 5, 'Alchemy'), (24, 5, 'Alchemy'), (25, 5, 'Alchemy'), (26, 5, 'Alchemy'),
    (27, 5, 'Alchemy')
;