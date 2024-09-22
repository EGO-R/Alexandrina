insert into users (id, username, password, role)
values (1, 'test@test.com', '{noop}123', 'USER');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

insert into playlist (id, name, created_by, created_at, modified_at)
values (1, '1', 'test@test.com', '2024-09-22 06:34:41.468437', '2024-09-22 06:34:41.468437'),
       (2, '2', 'test@test.com', '2024-09-22 06:34:41.468437', '2024-09-22 06:34:41.468437');
SELECT SETVAL('playlist_id_seq', (SELECT MAX(id) FROM playlist));

insert into video (id, name, created_by, created_at, modified_at)
values (1, 'paint the town red', 'test@test.com', '2024-09-22 06:34:41.468437', '2024-09-22 06:34:41.468437');
SELECT SETVAL('video_id_seq', (SELECT MAX(id) FROM video));

insert into playlist_video (id, playlist_id, video_id, created_by, created_at, modified_at)
values (1, 1, 1, 'test@test.com', '2024-09-22 06:34:41.468437', '2024-09-22 06:34:41.468437');
SELECT SETVAL('playlist_video_id_seq', (SELECT MAX(id) FROM playlist_video));