package io.github.shirohoo.springevent.controller;

import io.github.shirohoo.springevent.service.FileService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileService service;

    @PostMapping("/files/{id}")
    public ResponseEntity<?> upload(@PathVariable final Long id) {
        service.fileUpload(Map.of("id", id, "type", "image", "size", "5MB"));
        return ResponseEntity.ok(null);
    }

}
