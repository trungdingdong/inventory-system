package com.example.inventory_system.service;

import com.example.inventory_system.entity.Album;
import com.example.inventory_system.entity.Photo;
import com.example.inventory_system.entity.User;
import com.example.inventory_system.repository.AlbumRepository;
import com.example.inventory_system.repository.PhotoRepository;
import com.example.inventory_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private S3Service s3Service;

    public Photo uploadPhoto(Long userId, Long albumId, MultipartFile file) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Can't Find User"));

        String s3Url = s3Service.uploadFile(file);

        Photo photo = new Photo();
        photo.setS3url(s3Url);
        photo.setFileName(file.getOriginalFilename());
        photo.setUploadedAt(LocalDateTime.now());
        photo.setUser(user);

        if (albumId != null) {
            Album album = albumRepository.findById(albumId)
                    .orElseThrow(() -> new RuntimeException("Can't Find Album"));

            photo.setAlbum(album);
        }
        return photoRepository.save(photo);
    }

    public List<Photo> getPersonalPhoto(Long userId) {
        return photoRepository.findByUserId(userId);
    }

}
