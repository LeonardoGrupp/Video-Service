-- move genres, artist and album to right application

INSERT INTO genres (id, genre, total_likes, total_plays)
VALUES
    (9, 'Punk', 0, 0),
    (19, 'Synth', 0, 0);

INSERT INTO artist (name)
VALUES

    ('Ramones'), -- 108
    ('Alphaville'); -- 109

INSERT INTO album (album_name, release_date)
VALUES
    ('The Chromatica Ball', '2022-07-21'), -- 60
    ('Live At the Roxy', '1976-08-12'), -- 61
    ('40th Anniversary - The Symphonic Tour', '2024-08-02'); -- 62

INSERT INTO videos (id, type, title, url, release_date, play_counter, likes, dis_likes)
VALUES

    -- iron maiden - rock in rio
    (1, 'Video', 'The Wicker Man', 'url39', '2002-03-25', 0, 0, 0),
    (2, 'Video', 'Ghost Of the Navigator', 'url40', '2002-03-25', 0, 0, 0),
    (3, 'Video', 'Brave New World', 'url41', '2002-03-25', 0, 0, 0),
    (4, 'Video', 'Wrathchild', 'url42', '2002-03-25', 0, 0, 0),
    (5, 'Video', '2 Minutes To Midnight', 'url43', '2002-03-25', 0, 0, 0),
    (6, 'Video', 'Blood Brothers', 'url44', '2002-03-25', 0, 0, 0),
    (7, 'Video', 'Sign Of the Cross', 'url45', '2002-03-25', 0, 0, 0),
    (8, 'Video', 'The Mercenary', 'url46', '2002-03-25', 0, 0, 0),
    (9, 'Video', 'The Trooper', 'url47', '2002-03-25', 0, 0, 0),
    (10, 'Video', 'Dream Of Mirrors', 'url48', '2002-03-25', 0, 0, 0),
    (11, 'Video', 'The Clansman', 'url49', '2002-03-25', 0, 0, 0),
    (12, 'Video', 'The Evil That Men Do', 'url50', '2002-03-25', 0, 0, 0),
    (13, 'Video', 'Fear Of the Dark', 'url51', '2002-03-25', 0, 0, 0),
    (14, 'Video', 'Iron Maiden', 'url52', '2002-03-25', 0, 0, 0),
    (15, 'Video', 'The Number Of the Beast', 'url53', '2002-03-25', 0, 0, 0),
    (16, 'Video', 'Hallowed Be Thy Name', 'url54', '2002-03-25', 0, 0, 0),
    (17, 'Video', 'Sanctuary', 'url55', '2002-03-25', 0, 0, 0),
    (18, 'Video', 'Run To the Hills', 'url56', '2002-03-25', 0, 0, 0),

    -- dire straits - alchemy
    (19, 'Video', 'Once Upon A Time in the West', 'url57', '1984-03-16', 0, 0, 0),
    (20, 'Video', 'Romeo And Juliet', 'url58', '1984-03-16', 0, 0, 0),
    (21, 'Video', 'Expresso Love', 'url59', '1984-03-16', 0, 0, 0),
    (22, 'Video', 'Private Investigations', 'url60', '1984-03-16', 0, 0, 0),
    (23, 'Video', 'Sultans Of Swing', 'url61', '1984-03-16', 0, 0, 0),
    (24, 'Video', 'Tunnel Of Love', 'url62', '1984-03-16', 0, 0, 0),
    (25, 'Video', 'Telegraph Road', 'url63', '1984-03-16', 0, 0, 0),
    (26, 'Video', 'Solid Rock', 'url64', '1984-03-16', 0, 0, 0),
    (27, 'Video', 'Going Home - Theme From ''Local Hero''', 'url65', '1984-03-16', 0, 0, 0),

    -- lady gaga - the chromatica ball
    (28, 'Video', 'Bad Romance', 'url658', '2022-07-21', 0, 0, 0),
    (29, 'Video', 'Just Dance', 'url659', '2022-07-21', 0, 0, 0),
    (30, 'Video', 'Poker Face', 'url660', '2022-07-21', 0, 0, 0),
    (31, 'Video', 'Alice', 'url661', '2022-07-21', 0, 0, 0),
    (32, 'Video', 'Replay', 'url662', '2022-07-21', 0, 0, 0),
    (33, 'Video', 'Monster', 'url663', '2022-07-21', 0, 0, 0),
    (34, 'Video', '911', 'url664', '2022-07-21', 0, 0, 0),
    (35, 'Video', 'Sour Candy', 'url665', '2022-07-21', 0, 0, 0),
    (36, 'Video', 'Telephone', 'url666', '2022-07-21', 0, 0, 0),
    (37, 'Video', 'Lovegame', 'url667', '2022-07-21', 0, 0, 0),
    (38, 'Video', 'Babylon', 'url668', '2022-07-21', 0, 0, 0),
    (39, 'Video', 'Free Woman', 'url669', '2022-07-21', 0, 0, 0),
    (40, 'Video', 'Born This Way', 'url670', '2022-07-21', 0, 0, 0),
    (41, 'Video', 'Shallow', 'url671', '2022-07-21', 0, 0, 0),
    (42, 'Video', 'Always Remember Us This Way', 'url672', '2022-07-21', 0, 0, 0),
    (43, 'Video', 'THe Edge Of Glory', 'url673', '2022-07-21', 0, 0, 0),
    (44, 'Video', '1000 Doves', 'url674', '2022-07-21', 0, 0, 0),
    (45, 'Video', 'Fun Tonight', 'url675', '2022-07-21', 0, 0, 0),
    (46, 'Video', 'Enigma', 'url676', '2022-07-21', 0, 0, 0),
    (47, 'Video', 'Stupid Love', 'url677', '2022-07-21', 0, 0, 0),
    (48, 'Video', 'Hold My Hand', 'url678', '2022-07-21', 0, 0, 0),

    -- ramones - live at the roxy
    (49, 'Video', 'Loud Mouth', 'url679', '1976-08-12', 0, 0, 0),
    (50, 'Video', 'Beat On the Brat', 'url680', '1976-08-12', 0, 0, 0),
    (51, 'Video', 'Blitzkrieg Bop', 'url681', '1976-08-12', 0, 0, 0),
    (52, 'Video', 'I Remember You', 'url682', '1976-08-12', 0, 0, 0),
    (53, 'Video', 'Glad To See You Go', 'url683', '1976-08-12', 0, 0, 0),
    (54, 'Video', 'Chain Saw', 'url684', '1976-08-12', 0, 0, 0),
    (55, 'Video', '53rd & 3rd', 'url685', '1976-08-12', 0, 0, 0),
    (56, 'Video', 'I Wanna Be Your Boyfriend', 'url686', '1976-08-12', 0, 0, 0),
    (57, 'Video', 'Havana Affair', 'url687', '1976-08-12', 0, 0, 0),
    (58, 'Video', 'Listen To My Heart', 'url688', '1976-08-12', 0, 0, 0),
    (59, 'Video', 'California Sun', 'url689', '1976-08-12', 0, 0, 0),
    (60, 'Video', 'Judy is A Punk', 'url690', '1976-08-12', 0, 0, 0),
    (61, 'Video', 'I Don''t Wanna Walk Around With You', 'url691', '1976-08-12', 0, 0, 0),
    (62, 'Video', 'Today Your Love, Tomorrow the World', 'url692', '1976-08-12', 0, 0, 0),
    (63, 'Video', 'Now I Wanna Sniff Some Glue', 'url693', '1976-08-12', 0, 0, 0),
    (64, 'Video', 'Let''s Dance', 'url694', '1976-08-12', 0, 0, 0),

    -- alphaville - 40th anniversary - the symphonic tour
    (65, 'Video', 'Dream Machine', 'url695', '2024-08-02', 0, 0, 0),
    (66, 'Video', 'Big in Japan', 'url696', '2024-08-02', 0, 0, 0),
    (67, 'Video', 'Summer in Berlin', 'url697', '2024-08-02', 0, 0, 0),
    (68, 'Video', 'A Victory Of Love', 'url698', '2024-08-02', 0, 0, 0),
    (69, 'Video', 'Dance With Me', 'url699', '2024-08-02', 0, 0, 0),
    (70, 'Video', 'Sounds Like A Melody', 'url700', '2024-08-02', 0, 0, 0),
    (71, 'Video', 'Enigma', 'url701', '2024-08-02', 0, 0, 0),
    (72, 'Video', 'Elegy', 'url702', '2024-08-02', 0, 0, 0),
    (73, 'Video', 'Welcome To the Sun', 'url702', '2024-08-02', 0, 0, 0),
    (74, 'Video', 'Summer Rain', 'url703', '2024-08-02', 0, 0, 0),
    (75, 'Video', 'Moongirl', 'url704', '2024-08-02', 0, 0, 0),
    (76, 'Video', 'Apollo', 'url705', '2024-08-02', 0, 0, 0),
    (77, 'Video', 'Around the Universe', 'url706', '2024-08-02', 0, 0, 0),
    (78, 'Video', 'Flame', 'url707', '2024-08-02', 0, 0, 0),
    (79, 'Video', 'Eternally Yours', 'url708', '2024-08-02', 0, 0, 0),
    (80, 'Video', 'Lassie Come Home', 'url709', '2024-08-02', 0, 0, 0),
    (81, 'Video', 'Forever Young', 'url710', '2024-08-02', 0, 0, 0),
    (82, 'Video', 'Pandora''s Lullaby', 'url711', '2024-08-02', 0, 0, 0);

INSERT INTO videos_genres (videos_id, genre_id)
VALUES

    -- iron maiden -> heavy metal
    (1, 8), (2, 8), (3, 8), (4, 8), (5, 8), (6, 8), (7, 8), (8, 8), (9, 8), (10, 8),
    (11, 8), (12, 8), (13, 8), (14, 8), (15, 8), (16, 8), (17, 8), (18, 8),

    -- dire straits -> rock
    (19, 1), (20, 1), (21, 1), (22, 1), (23, 1), (24, 1), (25, 1), (26, 1), (27, 1),

    -- lady gaga -> pop
    (28, 2), (29, 2), (30, 2), (31, 2), (32, 2), (33, 2), (34, 2), (35, 2), (36, 2),
    (37, 2), (38, 2), (39, 2), (40, 2), (41, 2), (42, 2), (43, 2), (44, 2), (45, 2),
    (46, 2), (47, 2), (48, 2),

    -- ramones -> live at the roxy
    (49, 9), (50, 9), (51, 9), (52, 9), (53, 9), (54, 9), (55, 9), (56, 9), (57, 9),
    (58, 9), (59, 9), (60, 9), (61, 9), (62, 9), (63, 9), (64, 9),

    -- alphaville -> 40th anniversary - the symphonic tour
    (65, 19), (66, 19), (67, 19), (68, 19), (69, 19), (70, 19), (71, 19), (72, 19), (73, 19),
    (74, 19), (75, 19), (76, 19), (77, 19), (78, 19), (79, 19), (80, 19), (81, 19), (82, 19);

INSERT INTO videos_artists (videos_id, artist_id)
VALUES

    -- rock in rio -> iron maiden
    (1, 9), (2, 9), (3, 9), (4, 9), (5, 9), (6, 9), (7, 9), (8, 9), (9, 9), (10, 9),
    (11, 9), (12, 9), (13, 9), (14, 9), (15, 9), (16, 9), (17, 9), (18, 9),

    -- alchemy -> dire straits
    (19, 10), (20, 10), (21, 10), (22, 10), (23, 10), (24, 10), (25, 10), (26, 10), (27, 10),

    -- the chromatica ball -> lady gaga
    (28, 80), (29, 80), (30, 80), (31, 80), (32, 80), (33, 80), (34, 80), (35, 80), (36, 80),
    (37, 80), (38, 80), (39, 80), (40, 80), (41, 80), (42, 80), (43, 80), (44, 80), (45, 80),
    (46, 80), (47, 80), (48, 80),

    -- live at the roxy -> ramones
    (49, 108), (50, 108), (51, 108), (52, 108), (53, 108), (54, 108), (55, 108), (56, 108), (57, 108),
    (58, 108), (59, 108), (60, 108), (61, 108), (62, 108), (63, 108), (64, 108),

    -- 40th anniversary - the symphonic tour -> alphaville
    (65, 109), (66, 109), (67, 109), (68, 109), (69, 109), (70, 109), (71, 109), (72, 109), (73, 109),
    (74, 109), (75, 109), (76, 109), (77, 109), (78, 109), (79, 109), (80, 109), (81, 109), (82, 109);

INSERT INTO videos_albums (videos_id, album_id)
VALUES

    -- iron maiden -> rock in rio
    (1, 4), (2, 4), (3, 4), (4, 4), (5, 4), (6, 4), (7, 4), (8, 4), (9, 4),
    (10, 4), (11, 4), (12, 4), (13, 4), (14, 4), (15, 4), (16, 4), (17, 4), (18, 4),

    -- dire straits -> alchemy
    (19, 5), (20, 5), (21, 5), (22, 5), (23, 5), (24, 5), (25, 5), (26, 5), (27, 5),

    -- lady gaga -> the chromatica ball
    (28, 60), (29, 60), (30, 60), (31, 60), (32, 60), (33, 60), (34, 60), (35, 60), (36, 60),
    (37, 60), (38, 60), (39, 60), (40, 60), (41, 60), (42, 60), (43, 60), (44, 60), (45, 60),
    (46, 60), (47, 60), (48, 60),

    -- ramones -> live at the roxy
    (49, 61), (50, 61), (51, 61), (52, 61), (53, 61), (54, 61), (55, 61), (56, 61), (57, 61),
    (58, 61), (59, 61), (60, 61), (61, 61), (62, 61), (63, 61), (64, 61),

    -- alphaville -> 40th anniversary - the symphonic tour
    (65, 62), (66, 62), (67, 62), (68, 62), (69, 62), (70, 62), (71, 62), (72, 62), (73, 62),
    (74, 62), (75, 62), (76, 62), (77, 62), (78, 62), (79, 62), (80, 62), (81, 62), (82, 62);