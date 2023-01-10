package com.example.coffee.service.mainScreen.mainScreenImage;

import com.example.coffee.entity.mainScreen.MainScreenImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MainScreenImageService {
    void save(List<MultipartFile> inputImages) throws IOException;

    List<MainScreenImage> getAllImages();

    void deleteImageById(List<Long> ids) throws IOException;

}
