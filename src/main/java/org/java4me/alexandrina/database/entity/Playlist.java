package org.java4me.alexandrina.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "playlistVideos")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "playlist")
    private List<PlaylistVideo> playlistVideos = new ArrayList<>();
}
