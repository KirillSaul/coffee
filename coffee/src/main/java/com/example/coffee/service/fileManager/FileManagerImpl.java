package com.example.coffee.service.fileManager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileManagerImpl implements FileManager {
    public static final String ROOT_FOLDER = "coffee files";
    public static final Path ABSOLUTE_PATH = Paths.get(System.getProperty("user.dir")).getParent().resolve(Paths.get(ROOT_FOLDER));

    @Override
    public String save(MultipartFile file) throws IOException {
        final String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        createDirectory(ABSOLUTE_PATH);
        file.transferTo(ABSOLUTE_PATH.resolve(newFileName));
        return File.separator + Paths.get(ROOT_FOLDER, newFileName).toString();
    }

    private void createDirectory(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            Files.createDirectory(path);
        }
    }

    @Override
    public void delete(Path path) throws IOException {
        Files.delete(ABSOLUTE_PATH.resolve(path.getFileName()));
    }
}
