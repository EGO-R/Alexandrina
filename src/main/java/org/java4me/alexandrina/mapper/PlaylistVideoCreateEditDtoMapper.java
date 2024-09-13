package org.java4me.alexandrina.mapper;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.entity.PlaylistVideo;
import org.java4me.alexandrina.database.repository.PlaylistRepository;
import org.java4me.alexandrina.database.repository.VideoRepository;
import org.java4me.alexandrina.dto.PlaylistVideoCreateEditDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class PlaylistVideoCreateEditDtoMapper implements Mapper<PlaylistVideoCreateEditDto, PlaylistVideo> {
    private final PlaylistRepository playlistRepository;
    private final VideoRepository videoRepository;
    @Override
    public PlaylistVideo map(PlaylistVideoCreateEditDto obj) {
        var playlistVideo = new PlaylistVideo();
        playlistVideo.setVideo(videoRepository.findById(obj.getVideoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        playlistVideo.setPlaylist(playlistRepository.findById(obj.getPlaylistId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return playlistVideo;
    }
}
