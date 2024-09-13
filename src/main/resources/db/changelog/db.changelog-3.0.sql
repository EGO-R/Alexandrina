--liquibase formatted sql

--changeset EGO-R:1
alter table video
    add column modified_at timestamp not null ;

--changeset EGO-R:2
alter table playlist
    add column modified_at timestamp not null;

--changeset EGO-R:3
alter table playlist_video
    add column modified_at timestamp not null;

