package com.example.coffee.protocol.mainScreen;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@Component
public class MainScreenForm {
    @Min(value = 1)
    @Max(value = 5)
    private Integer imageSpeed;

    private List<MultipartFile> inputImages;
    private List<Long> idOldImagesForDelete;
}
