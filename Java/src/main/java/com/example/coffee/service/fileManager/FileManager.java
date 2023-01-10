package com.example.coffee.service.fileManager;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileManager {
    String save(MultipartFile file) throws IOException;

    void delete(Path path) throws IOException;
}
