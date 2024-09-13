package org.java4me.alexandrina.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@Builder
public class VideoReadDto {
    Long id;

    String name;

    List<PlaylistReadDto> playlists;

//    List<String> tags;
}
