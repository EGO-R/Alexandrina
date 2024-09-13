package org.java4me.alexandrina.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaylistVideoCreateEditDto {
    Long videoId;
    Integer playlistId;
}
