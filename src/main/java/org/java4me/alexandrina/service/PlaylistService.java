package org.java4me.alexandrina.service;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.repository.PlaylistRepository;
import org.java4me.alexandrina.database.repository.VideoRepository;
import org.java4me.alexandrina.dto.PlaylistCreateEditDto;
import org.java4me.alexandrina.dto.PlaylistReadDto;
import org.java4me.alexandrina.mapper.PlaylistCreateEditDtoMapper;
import org.java4me.alexandrina.mapper.PlaylistReadDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistReadDtoMapper playlistReadDtoMapper;
    private final PlaylistCreateEditDtoMapper playlistCreateEditDtoMapper;

    public List<PlaylistReadDto> findAll() {
        return playlistRepository.findAll().stream()
                .map(playlistReadDtoMapper::map)
                .toList();
    }

    public List<PlaylistReadDto> findExceptVideo(Long videoId) {
        return playlistRepository.findExceptVideo(videoId).stream()
                .map(playlistReadDtoMapper::map)
                .toList();
    }

    public Optional<PlaylistReadDto> findById(Integer id) {
        return playlistRepository.findById(id)
                .map(playlistReadDtoMapper::map);
    }

    @Transactional
    public PlaylistReadDto create(PlaylistCreateEditDto playlistCreateEditDto) {
        return Optional.of(playlistCreateEditDto)
                .map(playlistCreateEditDtoMapper::map)
                .map(playlistRepository::save)
                .map(playlistReadDtoMapper::map)
                .orElseThrow();
    }
}
