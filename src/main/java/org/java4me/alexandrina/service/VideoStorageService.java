package org.java4me.alexandrina.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class VideoStorageService {
    @Value("${app.video.bucket:C:/Users/Egor/IdeaProjects/Alexandrina/videos/}")
    private final String bucket;

    @Value("${app.video.postfix:.mp4}")
    private final String postfix;

    @SneakyThrows
    public void upload(String name, InputStream content) {
        var path = bucket + name + postfix;

        try (var output = new BufferedOutputStream(new FileOutputStream(path))) {
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int len = 0;
            while ((len = content.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
        }
    }

    @SneakyThrows
    public InputStreamResource getRange(String name, String range, HttpHeaders headers) {
        var file = new File(bucket + name + postfix);

        var fileLength = file.length();
        var ranges = range.replace("bytes=", "").split("-");
        var start = Long.parseLong(ranges[0]);
        var end = ranges.length > 1 ? Long.parseLong(ranges[1]) : fileLength - 1;
        var contentLength = end - start + 1;

        var inputStream = new FileInputStream(file);
        inputStream.skip(start);

        headers.add("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);
        headers.setContentLength(contentLength);
        headers.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath()));

        return new InputStreamResource(inputStream);
    }

    @SneakyThrows
    public FileSystemResource get(String name, HttpHeaders headers) {
        var file = new File(bucket + name + postfix);
        headers.setContentType(MediaType.parseMediaType(Files.probeContentType(file.toPath())));
        return new FileSystemResource(file);
    }

    public void delete(String name) {
        var file = new File(bucket + name + postfix);

        file.delete();
    }
}
