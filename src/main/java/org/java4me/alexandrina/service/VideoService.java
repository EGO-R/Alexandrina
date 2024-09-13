package org.java4me.alexandrina.service;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.entity.PlaylistVideo;
import org.java4me.alexandrina.database.repository.PlaylistVideoRepository;
import org.java4me.alexandrina.database.repository.SortType;
import org.java4me.alexandrina.database.repository.VideoRepository;
import org.java4me.alexandrina.dto.PlaylistVideoCreateEditDto;
import org.java4me.alexandrina.dto.VideoCreateEditDto;
import org.java4me.alexandrina.dto.VideoReadDto;
import org.java4me.alexandrina.mapper.PlaylistVideoCreateEditDtoMapper;
import org.java4me.alexandrina.mapper.VideoCreateEditDtoMapper;
import org.java4me.alexandrina.mapper.VideoReadDtoMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoReadDtoMapper videoReadDtoMapper;
    private final VideoCreateEditDtoMapper videoCreateEditDtoMapper;
    private final PlaylistVideoCreateEditDtoMapper playlistVideoCreateEditDtoMapper;
    private final PlaylistVideoRepository playlistVideoRepository;

    @Transactional
    public void toPlaylist(PlaylistVideoCreateEditDto playlistVideoCreateEditDto) {
        var playlistVideo = playlistVideoCreateEditDtoMapper.map(playlistVideoCreateEditDto);

        playlistVideoRepository.saveAndFlush(playlistVideo);
    }

    @Transactional
    public void fromPlaylist(PlaylistVideoCreateEditDto playlistVideoCreateEditDto) {
        var playlistVideo = playlistVideoRepository.findByPlaylistIdAndVideoId(
                        playlistVideoCreateEditDto.getPlaylistId(),
                        playlistVideoCreateEditDto.getVideoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        playlistVideoRepository.delete(playlistVideo);
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
