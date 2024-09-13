package org.java4me.alexandrina.database.repository;

import org.java4me.alexandrina.database.entity.Video;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("select p.video from PlaylistVideo p where p.playlist.id = :id")
    List<Video> findByPlaylist(Integer id, Sort sort);
}
