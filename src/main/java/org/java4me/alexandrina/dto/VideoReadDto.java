package org.java4me.alexandrina.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VideoReadDto {
    Long id;

    String name;

    List<PlaylistReadDto> playlists;

    String username;

//    List<String> tags;
}
