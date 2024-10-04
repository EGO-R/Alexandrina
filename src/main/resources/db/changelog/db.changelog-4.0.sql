--liquibase formatted sql

--changeset EGO-R:1
alter table video
    add column privacy varchar(32) not null default 'PUBLIC';

--changeset EGO-R:2
alter table playlist
    add column privacy varchar(32) not null default 'PUBLIC';




