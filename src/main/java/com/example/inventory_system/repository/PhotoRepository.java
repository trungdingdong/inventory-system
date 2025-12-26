package com.example.inventory_system.repository;

import com.example.inventory_system.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByUserId(Long userId);

    List<Photo> findByAlbumId(Long albumId);

}
