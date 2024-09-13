package org.java4me.alexandrina.integration.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.java4me.alexandrina.database.entity.Video;
import org.java4me.alexandrina.integration.IntegrationTestBase;
import org.java4me.alexandrina.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class VideoServiceTest extends IntegrationTestBase {

    private final VideoService videoService;

}
