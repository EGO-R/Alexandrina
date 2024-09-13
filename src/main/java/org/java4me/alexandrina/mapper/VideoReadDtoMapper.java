package org.java4me.alexandrina.mapper;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.entity.Playlist;
import org.java4me.alexandrina.database.entity.PlaylistVideo;
import org.java4me.alexandrina.database.entity.Video;
import org.java4me.alexandrina.dto.VideoReadDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class VideoReadDtoMapper implements Mapper<Video, VideoReadDto> {
    private final PlaylistReadDtoMapper playlistReadDtoMapper;

    @Override
    public VideoReadDto map(Video obj) {
        var playlists = new ArrayList<>(obj.getPlaylistVideos().stream()
                .map(PlaylistVideo::getPlaylist)
                .map(playlistReadDtoMapper::map)
                .toList());

        return VideoReadDto.builder()
                .id(obj.getId())
                .name(obj.getName())
                .playlists(playlists)
                .build();
    }
}
