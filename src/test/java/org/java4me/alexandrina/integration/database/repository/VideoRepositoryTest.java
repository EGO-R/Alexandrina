package org.java4me.alexandrina.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.database.entity.PlaylistVideo;
import org.java4me.alexandrina.database.repository.VideoRepository;
import org.java4me.alexandrina.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class VideoRepositoryTest  extends IntegrationTestBase {
    private final VideoRepository videoRepository;

    @Test
    void checkVideoByPlaylist() {
        var sort = Sort.sort(PlaylistVideo.class).by(PlaylistVideo::getModifiedAt).descending();
        var videos1 = videoRepository.findByPlaylist(1, sort);
        var videos2 = videoRepository.findByPlaylist(2, sort);

        assertThat(videos1).hasSize(1);
        assertThat(videos2).isEmpty();
    }
}
