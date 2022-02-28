package io.githun.shirohoo.files.storage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FileUploadControllerTests {
    @Autowired
    MockMvc mvc;

    @MockBean
    FileService fileService;

    @Test
    void shouldListAllFiles() throws Exception {
        given(fileService.loadAll())
            .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));

        mvc.perform(get("/files"))
            .andExpect(status().isOk())
            .andExpect(content().string("[\"http://localhost/files/first.txt\",\"http://localhost/files/second.txt\"]"));
    }

    @Test
    void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
            "text/plain", "Spring Framework".getBytes());

        given(fileService.load(any())).willReturn(Paths.get("test.txt"));

        mvc.perform(multipart("/files").file(multipartFile))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/files/test.txt"));

        then(fileService).should().store(multipartFile);
    }

    @Test
    void shouldServedFile() throws Exception {
        Files.createDirectory(Paths.get("files"));
        Path path = Paths.get("files/test.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        given(fileService.loadAsResource("test.txt"))
            .willReturn(new UrlResource(path.toUri()));

        mvc.perform(get("/files/test.txt"))
            .andExpect(status().isOk());

        Files.delete(path);
    }

    @Test
    void should404WhenMissingFile() throws Exception {
        given(fileService.loadAsResource("test.txt"))
            .willThrow(StorageFileNotFoundException.class);

        mvc.perform(get("/files/test.txt"))
            .andExpect(status().isNotFound());
    }

}
