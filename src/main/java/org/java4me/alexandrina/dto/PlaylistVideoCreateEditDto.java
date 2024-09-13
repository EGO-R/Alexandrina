package org.java4me.alexandrina.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaylistVideoCreateEditDto {
    @NotNull
    Long videoId;

    @NotNull
    Integer playlistId;
}
