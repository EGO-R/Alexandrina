package org.java4me.alexandrina.database.repository;

import org.java4me.alexandrina.database.entity.PlaylistVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideo, Long> {
    Optional<PlaylistVideo> findByPlaylistIdAndVideoId(Integer playlistId, Long videoId);
}
