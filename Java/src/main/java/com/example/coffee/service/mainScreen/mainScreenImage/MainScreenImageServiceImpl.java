package com.example.coffee.service.mainScreen.mainScreenImage;

import com.example.coffee.entity.mainScreen.MainScreenImage;
import com.example.coffee.repository.MainScreenImageRepository;
import com.example.coffee.service.fileManager.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainScreenImageServiceImpl implements MainScreenImageService {
    private final MainScreenImageRepository mainScreenImageRepository;
    private final FileManager fileManager;

    @Override
    public void save(List<MultipartFile> inputImages) throws IOException {
        try {
            for (MultipartFile inputImage : inputImages) {
                mainScreenImageRepository.save(MainScreenImage.builder().pathToImage(fileManager.save(inputImage)).build());
            }
        } catch (IOException e) {
            throw new IOException("Can`t save new image(s): " + e);
        }
    }

    @Override
    public List<MainScreenImage> getAllImages() {
        return mainScreenImageRepository.findAll();
    }

    @Override
    public void deleteImageById(List<Long> ids) throws IOException {
        for (Long id : ids) {
            try {
                fileManager.delete(Paths.get(mainScreenImageRepository.findById(id).orElseThrow().getPathToImage()));
            } catch (IOException e) {
                throw new IOException("Can`t find image(s) for delete on the device: " + e);
            }
            finally {
                mainScreenImageRepository.deleteById(id);
            }
        }

    }
}
