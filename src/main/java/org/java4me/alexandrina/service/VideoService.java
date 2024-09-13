package org.java4me.alexandrina.service;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.entity.PlaylistVideo;
import org.java4me.alexandrina.database.entity.Video;
import org.java4me.alexandrina.database.repository.PlaylistRepository;
import org.java4me.alexandrina.database.repository.SortType;
import org.java4me.alexandrina.database.repository.VideoRepository;
import org.java4me.alexandrina.dto.VideoCreateEditDto;
import org.java4me.alexandrina.dto.VideoReadDto;
import org.java4me.alexandrina.mapper.VideoCreateEditDtoMapper;
import org.java4me.alexandrina.mapper.VideoReadDtoMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoReadDtoMapper videoReadDtoMapper;
    private final VideoCreateEditDtoMapper videoCreateEditDtoMapper;

    @Transactional
    public void updatePlaylists(Long videoId, Set<Integer> playlists) {
        var optionalVideo = videoRepository.findById(videoId);


        var video = optionalVideo
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));



        videoRepository.flush();
    }

    public List<VideoReadDto> findVideosInPlaylist(Integer playlistId) {
        return findVideosInPlaylistSorted(playlistId, SortType.DESC);
    }

    public List<VideoReadDto> findVideosInPlaylistSorted(Integer playlistId, SortType sortType) {
        var typedSort = Sort.sort(PlaylistVideo.class).by(PlaylistVideo::getModifiedAt);
        Sort sort;

        if (sortType == SortType.ASC)
            sort = typedSort.ascending();
        else
            sort = typedSort.descending();

        return videoRepository.findByPlaylist(playlistId, sort).stream()
                .map(videoReadDtoMapper::map)
                .toList();
    }


    public List<VideoReadDto> findAll() {
        return videoRepository.findAll().stream()
                .map(videoReadDtoMapper::map)
                .toList();
    }

    public Optional<VideoReadDto> findById(Long id) {
        return videoRepository.findById(id)
                .map(videoReadDtoMapper::map);
    }

    @Transactional
    public VideoReadDto create(VideoCreateEditDto videoCreateEditDto) {
        return Optional.of(videoCreateEditDto)
                .map(videoCreateEditDtoMapper::map)
                .map(videoRepository::save)
                .map(videoReadDtoMapper::map)
                .orElseThrow();
    }
}
