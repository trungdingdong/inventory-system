package com.example.inventory_system.Controller;

import com.example.inventory_system.entity.Photo;
import com.example.inventory_system.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "albumId", required = false) Long albumId,
            @RequestParam("file") MultipartFile file) {
        try {
            Photo savedPhoto = photoService.uploadPhoto(userId, albumId, file);
            return ResponseEntity.ok(savedPhoto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Photo>> getPhotosByUser(@PathVariable Long userId){
        return ResponseEntity.ok(photoService.getPersonalPhoto(userId));
    }

    @GetMapping("")
    public ResponseEntity<List<Photo>> getAllPhotos(){
        return ResponseEntity.ok(photoService.getAllPhotos());
    }
}
