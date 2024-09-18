package org.java4me.alexandrina.integration.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.java4me.alexandrina.integration.IntegrationTestBase;
import org.java4me.alexandrina.service.VideoStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;

import java.io.InputStream;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
@RequiredArgsConstructor
class VideoStorageServiceTest extends IntegrationTestBase {
    @Mock
    private HttpHeaders headers;

    private final String range = "bytes=0-";
    private final String name = "4";
    private final VideoStorageService videoStorageService;


    @Test
    @SneakyThrows
    void checkFileSize() {
        var result = videoStorageService.getRange(name, range, headers);
        var inputStream = result.getInputStream();
        long counter = 0;
        var buffer = new byte[1];
        while (inputStream.read(buffer) != -1) {
            counter++;
        }
        System.out.println(counter);
    }

}