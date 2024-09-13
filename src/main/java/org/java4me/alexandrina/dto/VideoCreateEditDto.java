package org.java4me.alexandrina.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VideoCreateEditDto {
    @NotNull
    String name;
}
