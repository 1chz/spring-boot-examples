package io.githun.shirohoo.files.storage;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileUploadController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<String>> listUploadedFiles() {
        return ResponseEntity.ok()
            .body(fileService.loadAll()
                .map(this::serveFiles)
                .collect(toList())
            );
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileService.loadAsResource(filename);
        String attachment = "attachment; filename=\"" + file.getFilename() + "\"";
        return ResponseEntity.ok()
            .header(CONTENT_DISPOSITION, attachment)
            .body(file);
    }

    @PostMapping
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
        fileService.store(file);
        Path filePath = fileService.load(file.getOriginalFilename());
        return ResponseEntity.created(URI.create(serveFiles(filePath))).build();
    }

    public String serveFiles(Path filePath) {
        String args = filePath.getFileName().toString();
        return fromMethodName(FileUploadController.class, "serveFile", args)
            .build()
            .toUri()
            .toString();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
