package org.java4me.alexandrina.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "id")
@Builder
public class PlaylistReadDto {
    @NotNull
    Integer id;
    @NotNull
    String name;
}
