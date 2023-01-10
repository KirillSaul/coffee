package com.example.coffee.entity.mainScreen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "main_screen_images", schema = "coffee", catalog = "coffee")
public class MainScreenImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_main_screen_picture", nullable = false)
    private Long idMainScreenPicture;

    @Column(name = "path_to_image", nullable = false, length = 255)
    private String pathToImage;
}
