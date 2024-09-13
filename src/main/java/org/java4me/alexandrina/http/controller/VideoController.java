package org.java4me.alexandrina.http.controller;

import lombok.RequiredArgsConstructor;
import org.java4me.alexandrina.dto.VideoCreateEditDto;
import org.java4me.alexandrina.dto.VideoReadDto;
import org.java4me.alexandrina.service.PlaylistService;
import org.java4me.alexandrina.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final PlaylistService playlistService;

    @GetMapping
    public String getAllVideos(Model model) {
        model.addAttribute("videos", videoService.findAll());
        return "content/videos";
    }

    @GetMapping("/{id}")
    public String getVideoById(@PathVariable("id") Long id,
                               Model model) {
        var video = videoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("video", video);

        model.addAttribute("playlists", playlistService.findExceptVideo(video.getId()));

        return "content/video";
    }

    @GetMapping("/create")
    public String createForm() {
        return "content/new_video";
    }

    @PostMapping("/create")
    public String create(@Validated VideoCreateEditDto video) {
        return "redirect:/videos/" + videoService.create(video).getId();
    }

    @PostMapping("/{id}/toPlaylist")
    public String toPlaylist(@RequestParam Optional<Set<Integer>> optPlaylists,
                             @PathVariable("id") Long videoId) {
        var playlists = optPlaylists.orElseGet(HashSet::new);
        return "redirect:/videos/{id}";
    }
}
