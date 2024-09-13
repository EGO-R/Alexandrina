insert into playlist (id, name)
values (1, '1'),
       (2, '2');
SELECT SETVAL('playlist_id_seq', (SELECT MAX(id) FROM playlist));

insert into video (id, location)
values (1, 'paint the town red');
SELECT SETVAL('video_id_seq', (SELECT MAX(id) FROM video));

insert into playlist_video (playlist_id, video_id, playlist_position)
values (1, 1, 1);
SELECT SETVAL('playlist_video_id_seq', (SELECT MAX(id) FROM playlist_video));
