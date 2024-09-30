INSERT INTO genres (id, genre, type, total_likes, total_plays)
VALUES
    (20, 'Heavy Metal', 'video', 0,0),
    (21, 'Rock', 'video', 0,0),
    (22, 'Pop', 'video', 0,0),
    (23, 'Punk', 'video', 0, 0),
    (24, 'Synth', 'video', 0, 0);

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
    (1, 'Video', 'The Wicker Man', 'url1001', '2002-03-25', 0, 0, 0),
    (2, 'Video', 'Ghost Of the Navigator', 'url1002', '2002-03-25', 0, 0, 0),
    (3, 'Video', 'Brave New World', 'url1003', '2002-03-25', 0, 0, 0),
    (4, 'Video', 'Wrathchild', 'url1004', '2002-03-25', 0, 0, 0),
    (5, 'Video', '2 Minutes To Midnight', 'url1005', '2002-03-25', 0, 0, 0),
    (6, 'Video', 'Blood Brothers', 'url1006', '2002-03-25', 0, 0, 0),
    (7, 'Video', 'Sign Of the Cross', 'url1007', '2002-03-25', 0, 0, 0),
    (8, 'Video', 'The Mercenary', 'url1008', '2002-03-25', 0, 0, 0),
    (9, 'Video', 'The Trooper', 'url1009', '2002-03-25', 0, 0, 0),
    (10, 'Video', 'Dream Of Mirrors', 'url1010', '2002-03-25', 0, 0, 0),
    (11, 'Video', 'The Clansman', 'url1011', '2002-03-25', 0, 0, 0),
    (12, 'Video', 'The Evil That Men Do', 'url1012', '2002-03-25', 0, 0, 0),
    (13, 'Video', 'Fear Of the Dark', 'url1013', '2002-03-25', 0, 0, 0),
    (14, 'Video', 'Iron Maiden', 'url1014', '2002-03-25', 0, 0, 0),
    (15, 'Video', 'The Number Of the Beast', 'url1015', '2002-03-25', 0, 0, 0),
    (16, 'Video', 'Hallowed Be Thy Name', 'url1016', '2002-03-25', 0, 0, 0),
    (17, 'Video', 'Sanctuary', 'url1017', '2002-03-25', 0, 0, 0),
    (18, 'Video', 'Run To the Hills', 'url1018', '2002-03-25', 0, 0, 0),

    -- dire straits - alchemy
    (19, 'Video', 'Once Upon A Time in the West', 'url1019', '1984-03-16', 0, 0, 0),
    (20, 'Video', 'Romeo And Juliet', 'url1020', '1984-03-16', 0, 0, 0),
    (21, 'Video', 'Expresso Love', 'url1021', '1984-03-16', 0, 0, 0),
    (22, 'Video', 'Private Investigations', 'url1022', '1984-03-16', 0, 0, 0),
    (23, 'Video', 'Sultans Of Swing', 'url1023', '1984-03-16', 0, 0, 0),
    (24, 'Video', 'Tunnel Of Love', 'url1024', '1984-03-16', 0, 0, 0),
    (25, 'Video', 'Telegraph Road', 'url1025', '1984-03-16', 0, 0, 0),
    (26, 'Video', 'Solid Rock', 'url1026', '1984-03-16', 0, 0, 0),
    (27, 'Video', 'Going Home - Theme From ''Local Hero''', 'url1027', '1984-03-16', 0, 0, 0),

    -- lady gaga - the chromatica ball
    (28, 'Video', 'Bad Romance', 'url1028', '2022-07-21', 0, 0, 0),
    (29, 'Video', 'Just Dance', 'url1029', '2022-07-21', 0, 0, 0),
    (30, 'Video', 'Poker Face', 'url1030', '2022-07-21', 0, 0, 0),
    (31, 'Video', 'Alice', 'url1031', '2022-07-21', 0, 0, 0),
    (32, 'Video', 'Replay', 'url1032', '2022-07-21', 0, 0, 0),
    (33, 'Video', 'Monster', 'url1033', '2022-07-21', 0, 0, 0),
    (34, 'Video', '911', 'url1034', '2022-07-21', 0, 0, 0),
    (35, 'Video', 'Sour Candy', 'url1035', '2022-07-21', 0, 0, 0),
    (36, 'Video', 'Telephone', 'url1036', '2022-07-21', 0, 0, 0),
    (37, 'Video', 'Lovegame', 'url1037', '2022-07-21', 0, 0, 0),
    (38, 'Video', 'Babylon', 'url1038', '2022-07-21', 0, 0, 0),
    (39, 'Video', 'Free Woman', 'url1039', '2022-07-21', 0, 0, 0),
    (40, 'Video', 'Born This Way', 'url1040', '2022-07-21', 0, 0, 0),
    (41, 'Video', 'Shallow', 'url1041', '2022-07-21', 0, 0, 0),
    (42, 'Video', 'Always Remember Us This Way', 'url1042', '2022-07-21', 0, 0, 0),
    (43, 'Video', 'THe Edge Of Glory', 'url1043', '2022-07-21', 0, 0, 0),
    (44, 'Video', '1000 Doves', 'url1044', '2022-07-21', 0, 0, 0),
    (45, 'Video', 'Fun Tonight', 'url1045', '2022-07-21', 0, 0, 0),
    (46, 'Video', 'Enigma', 'url1046', '2022-07-21', 0, 0, 0),
    (47, 'Video', 'Stupid Love', 'url1047', '2022-07-21', 0, 0, 0),
    (48, 'Video', 'Hold My Hand', 'url1048', '2022-07-21', 0, 0, 0),

    -- ramones - live at the roxy
    (49, 'Video', 'Loud Mouth', 'url1049', '1976-08-12', 0, 0, 0),
    (50, 'Video', 'Beat On the Brat', 'url1050', '1976-08-12', 0, 0, 0),
    (51, 'Video', 'Blitzkrieg Bop', 'url1051', '1976-08-12', 0, 0, 0),
    (52, 'Video', 'I Remember You', 'url1052', '1976-08-12', 0, 0, 0),
    (53, 'Video', 'Glad To See You Go', 'url1053', '1976-08-12', 0, 0, 0),
    (54, 'Video', 'Chain Saw', 'url1054', '1976-08-12', 0, 0, 0),
    (55, 'Video', '53rd & 3rd', 'url1055', '1976-08-12', 0, 0, 0),
    (56, 'Video', 'I Wanna Be Your Boyfriend', 'url1056', '1976-08-12', 0, 0, 0),
    (57, 'Video', 'Havana Affair', 'url1057', '1976-08-12', 0, 0, 0),
    (58, 'Video', 'Listen To My Heart', 'url1058', '1976-08-12', 0, 0, 0),
    (59, 'Video', 'California Sun', 'url1059', '1976-08-12', 0, 0, 0),
    (60, 'Video', 'Judy is A Punk', 'url1060', '1976-08-12', 0, 0, 0),
    (61, 'Video', 'I Don''t Wanna Walk Around With You', 'url1061', '1976-08-12', 0, 0, 0),
    (62, 'Video', 'Today Your Love, Tomorrow the World', 'url1062', '1976-08-12', 0, 0, 0),
    (63, 'Video', 'Now I Wanna Sniff Some Glue', 'url1063', '1976-08-12', 0, 0, 0),
    (64, 'Video', 'Let''s Dance', 'url1064', '1976-08-12', 0, 0, 0),

    -- alphaville - 40th anniversary - the symphonic tour
    (65, 'Video', 'Dream Machine', 'url1065', '2024-08-02', 0, 0, 0),
    (66, 'Video', 'Big in Japan', 'url1066', '2024-08-02', 0, 0, 0),
    (67, 'Video', 'Summer in Berlin', 'url1067', '2024-08-02', 0, 0, 0),
    (68, 'Video', 'A Victory Of Love', 'url1068', '2024-08-02', 0, 0, 0),
    (69, 'Video', 'Dance With Me', 'url1069', '2024-08-02', 0, 0, 0),
    (70, 'Video', 'Sounds Like A Melody', 'url1070', '2024-08-02', 0, 0, 0),
    (71, 'Video', 'Enigma', 'url1071', '2024-08-02', 0, 0, 0),
    (72, 'Video', 'Elegy', 'url1072', '2024-08-02', 0, 0, 0),
    (73, 'Video', 'Welcome To the Sun', 'url1073', '2024-08-02', 0, 0, 0),
    (74, 'Video', 'Summer Rain', 'url1074', '2024-08-02', 0, 0, 0),
    (75, 'Video', 'Moongirl', 'url1075', '2024-08-02', 0, 0, 0),
    (76, 'Video', 'Apollo', 'url1076', '2024-08-02', 0, 0, 0),
    (77, 'Video', 'Around the Universe', 'url1077', '2024-08-02', 0, 0, 0),
    (78, 'Video', 'Flame', 'url1078', '2024-08-02', 0, 0, 0),
    (79, 'Video', 'Eternally Yours', 'url1079', '2024-08-02', 0, 0, 0),
    (80, 'Video', 'Lassie Come Home', 'url1080', '2024-08-02', 0, 0, 0),
    (81, 'Video', 'Forever Young', 'url1081', '2024-08-02', 0, 0, 0),
    (82, 'Video', 'Pandora''s Lullaby', 'url1082', '2024-08-02', 0, 0, 0);

INSERT INTO videos_genres (videos_id, genre_id)
VALUES

    -- iron maiden -> heavy metal
    (1, 20), (2, 20), (3, 20), (4, 20), (5, 20), (6, 20), (7, 20), (8, 20), (9, 20), (10, 20),
    (11, 20), (12, 20), (13, 20), (14, 20), (15, 20), (16, 20), (17, 20), (18, 20),

    -- dire straits -> rock
    (19, 21), (20, 21), (21, 21), (22, 21), (23, 21), (24, 21), (25, 21), (26, 21), (27, 21),

    -- lady gaga -> pop
    (28, 22), (29, 22), (30, 22), (31, 22), (32, 22), (33, 22), (34, 22), (35, 22), (36, 22),
    (37, 22), (38, 22), (39, 22), (40, 22), (41, 22), (42, 22), (43, 22), (44, 22), (45, 22),
    (46, 22), (47, 22), (48, 22),

    -- ramones -> live at the roxy
    (49, 23), (50, 23), (51, 23), (52, 23), (53, 23), (54, 23), (55, 23), (56, 23), (57, 23),
    (58, 23), (59, 23), (60, 23), (61, 23), (62, 23), (63, 23), (64, 23),

    -- alphaville -> 40th anniversary - the symphonic tour
    (65, 24), (66, 24), (67, 24), (68, 24), (69, 24), (70, 24), (71, 24), (72, 24), (73, 24),
    (74, 24), (75, 24), (76, 24), (77, 24), (78, 24), (79, 24), (80, 24), (81, 24), (82, 24);

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