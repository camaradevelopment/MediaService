package com.camaradevelopment.media.service.models.media;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media,Integer> {
        Media findById(int id);
}
