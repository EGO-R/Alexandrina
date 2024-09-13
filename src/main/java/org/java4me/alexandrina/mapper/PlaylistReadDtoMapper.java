package org.java4me.alexandrina.mapper;

import org.java4me.alexandrina.database.entity.Playlist;
import org.java4me.alexandrina.dto.PlaylistReadDto;
import org.springframework.stereotype.Component;

@Component
public class PlaylistReadDtoMapper implements Mapper<Playlist, PlaylistReadDto> {
    @Override
    public PlaylistReadDto map(Playlist obj) {
        return PlaylistReadDto.builder()
                .id(obj.getId())
                .name(obj.getName())
                .build();
    }
}
