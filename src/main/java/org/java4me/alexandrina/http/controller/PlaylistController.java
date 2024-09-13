package org.java4me.alexandrina.http.controller;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.dto.PlaylistCreateEditDto;
import org.java4me.alexandrina.dto.VideoCreateEditDto;
import org.java4me.alexandrina.service.PlaylistService;
import org.java4me.alexandrina.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final VideoService videoService;

    @GetMapping
    public String getAllPlaylists(Model model) {
        model.addAttribute("playlists", playlistService.findAll());
        return "content/playlists";
    }

    @GetMapping("/{id}")
    public String getPlaylist(Model model,
                              @PathVariable("id") Integer id) {
        // TODO: 11.09.2024 check sort
        playlistService.findById(id)
                        .map(playlist ->
                                model.addAttribute("playlistName", playlist.getName()))
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("videos", videoService.findVideosInPlaylist(id));

        return "content/playlist";
    }

    @GetMapping("/create")
    public String createForm() {
        return "content/new_playlist";
    }

    @PostMapping("/create")
    public String create(@Validated PlaylistCreateEditDto playlist) {
        return "redirect:/playlists/" + playlistService.create(playlist).getId();
    }
}
