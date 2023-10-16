package com.example.mainapp.controller;

import com.example.mainapp.entity.Image;
import com.example.mainapp.repository.ImageRepo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.ByteArrayInputStream;

@Controller
public class ImageController {

    public ImageRepo imageRepo;

    @GetMapping("/images/{id}") // принимает айди картинки
    public ResponseEntity<?> getImageById(@PathVariable Long id) throws Exception {
        Image image = imageRepo.findById(id).orElseThrow(() ->
                new Exception("Image not found with id: " + id));
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName()) // имя картинке
                .contentType(MediaType.valueOf(image.getContentType())) // преобразовываем картинку
                .contentLength(image.getSize()) // устанавливаем размер картинки
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
